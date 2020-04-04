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
    void shouldBeLoanedByMember() {
        Member member = new Member("Name", "username", "Address", "email");
        book.loanedBy(member);

        assertEquals(member, book.getBorrower());
    }

    @Test
    void shouldBeReservedByMember() {
        Member member = new Member("Name", "username", "Address", "email");
        book.reservedBy(member);

        assertEquals(member, book.getReserver());
    }

    @Test
    void shouldGetBookName() {
        assertEquals("Name", book.getBookDetail().getName());
    }

    @Test
    void shouldGetAuthorName() {
        assertEquals("Author", book.getBookDetail().getAuthor());
    }

    @Test
    void shouldGetUID() {
        assertEquals("uid", book.getUid());
    }
}