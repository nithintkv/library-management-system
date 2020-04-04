package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private BookDetail bookDetail;
    private Book book;

    @BeforeEach
    void createObjects() {
        bookDetail = new BookDetail("Name", "Author", "Subject");
        book = new Book(bookDetail, "uid");
    }

    @Test
    void shouldBeAvailable() {
        assertTrue(book.isAvailable());
    }

    @Test
    void shouldNotBeAvailable() {
        book.setStatus(BookStatus.CHECKED_OUT);
        assertFalse(book.isAvailable());
    }

    @Test
    void shouldNotBeAvailableIfReserved() {
        book.setStatus(BookStatus.RESERVED);
        assertFalse(book.isAvailable());
    }
}