package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryRepositoryTest {
    private Repository libraryRepo;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void createObjects() {
        libraryRepo = new LibraryRepository();
        BookDetail bookDetail1 = new BookDetail("Name1", "Author1", "Subject1");
        BookDetail bookDetail2 = new BookDetail("Name2", "Author2", "Subject2");
        book1 = new Book(bookDetail1, "uid");
        book2 = new Book(bookDetail1, "uid2");
        book3 = new Book(bookDetail2, "uid3");
        libraryRepo.addBook(book1);
        libraryRepo.addBook(book2);
        libraryRepo.addBook(book3);
    }

    @Test
    void shouldGetEmptyListOfBooks() {
        assertEquals(List.of(), libraryRepo.searchByAuthor("Author"));
    }

    @Test
    void shouldGetListOfBooksByAuthor() {
        assertEquals(List.of(book1, book2), libraryRepo.searchByAuthor("Author1"));
        assertEquals(List.of(book3), libraryRepo.searchByAuthor("Author2"));
    }

    @Test
    void shouldGetListOfBooksByName() {
        assertEquals(List.of(book1, book2), libraryRepo.searchByName("Name1"));
        assertEquals(List.of(book3), libraryRepo.searchByName("Name2"));
    }

    @Test
    void shouldGetSingleBookByUID() {
        assertEquals(book1, libraryRepo.searchById("uid"));
        assertEquals(book2, libraryRepo.searchById("uid2"));
        assertEquals(book3, libraryRepo.searchById("uid3"));
    }
}