package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;
    private Repository libraryRepo;

    @BeforeEach
    void createObjects() {
        libraryService = new LibraryService();
        libraryRepo = new LibraryRepository();
        libraryService.setLibraryRepo(libraryRepo);
    }

    @Test
    void shouldAddBookToCatalog() {
        libraryService.addBook("Name", "Author", "Subject", "uid");

        assertEquals("uid", libraryRepo.searchById("uid").getUid());
    }

    @Test
    void shouldAddSameBookWithDifferentUIDs() {
        libraryService.addBook("Name", "Author", "Subject", "uid1");
        libraryService.addBook("Name", "Author", "Subject", "uid2");

        List<Book> books = libraryRepo.searchByAuthor("Author");
        assertEquals(books.get(0).getBookDetail(), books.get(1).getBookDetail());
    }

    @Test
    void shouldAddDifferentBookWithDifferentAuthors() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        libraryService.addBook("Name2", "Author2", "Subject2", "uid2");

        List<Book> books1 = libraryRepo.searchByAuthor("Author1");
        List<Book> books2 = libraryRepo.searchByAuthor("Author2");
        assertNotEquals(books1.get(0).getBookDetail(), books2.get(0).getBookDetail());
    }

    @Test
    void shouldCheckoutBookForMember() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member = new Member("member_name", "member_username", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        assertTrue(libraryService.checkout(member, book));
    }

    @Test
    void shouldReserveBookForMemberIfDifferentMemberCheckedOut() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member = new Member("member_name", "member_username", "address", "email");
        Member member2 = new Member("member_name", "member_username2", "address", "email");

        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member, book);

        assertTrue(libraryService.reserve(member2, book));
    }

    @Test
    void shouldNotReserveBookForMemberIfAlreadyReserved() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member = new Member("member_name", "member_username", "address", "email");
        Member member2 = new Member("member_name", "member_username2", "address", "email");
        Member member3 = new Member("member_name", "member_username3", "address", "email");

        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member, book);

        assertTrue(libraryService.reserve(member2, book));
        assertFalse(libraryService.reserve(member3, book));
    }

    @Test
    void shouldNotReserveBookForMemberIfSameMemberCheckedOut() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member = new Member("member_name", "member_username", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member, book);

        assertFalse(libraryService.reserve(member, book));
    }

    @Test
    void shouldNotReserveBookForMemberIfBookNotCheckedOut() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member = new Member("member_name", "member_username", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        assertFalse(libraryService.reserve(member, book));
    }

    @Test
    void shouldNotCheckoutBookIfAlreadyCheckedOut() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Member member2 = new Member("member_name", "member_username2", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member1, book);

        assertFalse(libraryService.checkout(member2, book));
    }

    @Test
    void shouldGetBooksCheckedOutByMember() {
        libraryService.addBook("Name1", "Author", "Subject2", "uid1");
        libraryService.addBook("Name2", "Author", "Subject2", "uid2");
        Member member1 = new Member("member_name", "member_username1", "address", "email");

        List<Book> books = libraryRepo.searchByAuthor("Author");

        libraryService.checkout(member1, books.get(0));
        libraryService.checkout(member1, books.get(1));

        List<Book> booksByMember = libraryService.getBooksCheckedOutBy(member1);

        assertTrue(booksByMember.containsAll(books));
    }

    @Test
    void shouldGetBooksReservedByMember() {
        libraryService.addBook("Name1", "Author", "Subject2", "uid1");
        libraryService.addBook("Name2", "Author", "Subject2", "uid2");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Member member2 = new Member("member_name", "member_username2", "address", "email");

        List<Book> books = libraryRepo.searchByAuthor("Author");

        libraryService.checkout(member1, books.get(0));
        libraryService.checkout(member1, books.get(1));
        libraryService.reserve(member2, books.get(0));
        libraryService.reserve(member2, books.get(1));

        List<Book> booksByMember = libraryService.getBooksReservedBy(member2);

        assertTrue(booksByMember.containsAll(books));
    }

    @Test
    void shouldReturnBook() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member1, book);

        assertTrue(libraryService.returnBook(book));
    }

    @Test
    void shouldNotReturnBookWhichIsAvailable() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Book book = libraryRepo.searchById("uid1");

        assertFalse(libraryService.returnBook(book));
    }

    @Test
    void shouldChangeStatusOfBookAfterReturning() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member1, book);
        libraryService.returnBook(book);

        assertEquals(BookStatus.AVAILABLE, book.getStatus());
    }

    @Test
    void shouldNotChangeStatusOfBookIfReservedAfterReturning() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Member member2 = new Member("member_name", "member_username2", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member1, book);
        libraryService.reserve(member2, book);
        libraryService.returnBook(book);

        assertEquals(BookStatus.RESERVED, book.getStatus());
    }

    @Test
    void shouldRemoveBorrowerAfterReturning() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member1, book);
        libraryService.returnBook(book);

        assertNull(book.getBorrower());
    }

    @Test
    void shouldClearBookFromListOfMemberAfterReturning() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        Member member1 = new Member("member_name", "member_username1", "address", "email");
        Book book = libraryRepo.searchById("uid1");

        libraryService.checkout(member1, book);

        assertEquals(List.of(book), libraryService.getBooksCheckedOutBy(member1));

        libraryService.returnBook(book);

        assertEquals(List.of(), libraryService.getBooksCheckedOutBy(member1));
    }

    @Test
    void shouldNotCheckoutNewBookIfLimitReachedForMember() {
        libraryService.addBook("Name1", "Author1", "Subject2", "uid1");
        libraryService.addBook("Name2", "Author1", "Subject2", "uid2");
        libraryService.addBook("Name3", "Author1", "Subject2", "uid3");
        libraryService.addBook("Name4", "Author1", "Subject2", "uid4");
        libraryService.addBook("Name5", "Author1", "Subject2", "uid5");
        libraryService.addBook("Name6", "Author6", "Subject6", "uid6");
        Member member1 = new Member("member_name", "member_username1", "address", "email");

        for (Book book : libraryRepo.searchByAuthor("Author1")) {
            assertTrue(libraryService.checkout(member1, book));
        }

        assertFalse(libraryService.checkout(member1, libraryRepo.searchById("uid6")));
    }
}