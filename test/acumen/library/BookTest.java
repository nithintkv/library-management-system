package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void createObjects() {
        BookDetail bookDetail = new BookDetail("Name", "Author", "Subject");
        book = new Book(bookDetail, "uid");
    }

    @Test
    void shouldBeAvailable() {
        assertTrue(book.isAvailable());
    }

    @Test
    void shouldNotBeAvailableIfCheckedOut() {
        book.setStatus(BookStatus.CHECKED_OUT);

        assertFalse(book.isAvailable());
    }

    @Test
    void shouldNotBeAvailableIfReserved() {
        book.setStatus(BookStatus.RESERVED);

        assertFalse(book.isAvailable());
    }

    @Test
    void shouldGetBookName() {
        assertEquals("Name", book.getName());
    }

    @Test
    void shouldGetAuthorName() {
        assertEquals("Author", book.getAuthor());
    }

    @Test
    void shouldGetUID() {
        assertEquals("uid", book.getUid());
    }
}