package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    private Member member;
    private BookDetail bookDetail;
    private Book book;

    @BeforeEach
    void createObjects() {
        member = new Member("Name", "username", "Address", "email");
        bookDetail = new BookDetail("Name", "Author", "Subject");
        book = new Book(bookDetail, "uid");
    }

    @Test
    void canary() {
        assertTrue(true);
    }

    @Test
    void shouldNotReserveNewBookIfNotCheckedOut() {
        assertFalse(member.reserve(book));
    }

    @Test
    void shouldReserveBookIfCheckedOut() {
        book.setStatus(BookStatus.CHECKED_OUT);
        assertTrue(member.reserve(book));
    }

    @Test
    void shouldGetReservedBooks() {
        book.setStatus(BookStatus.CHECKED_OUT);
        member.reserve(book);

        assertEquals(Set.of(book), member.getReservedBooks());
    }

    @Test
    void shouldNotReserveNewBookIfLimitReached() {
        Book book2 = new Book(bookDetail, "uid2");
        Book book3 = new Book(bookDetail, "uid3");
        Book book4 = new Book(bookDetail, "uid4");
        Book book5 = new Book(bookDetail, "uid5");
        Book book6 = new Book(bookDetail, "uid6");
        book.setStatus(BookStatus.CHECKED_OUT);
        book2.setStatus(BookStatus.CHECKED_OUT);
        book3.setStatus(BookStatus.CHECKED_OUT);
        book4.setStatus(BookStatus.CHECKED_OUT);
        book5.setStatus(BookStatus.CHECKED_OUT);
        book6.setStatus(BookStatus.CHECKED_OUT);


        assertTrue(member.reserve(book));
        assertTrue(member.reserve(book2));
        assertTrue(member.reserve(book3));
        assertTrue(member.reserve(book4));
        assertTrue(member.reserve(book5));
        assertFalse(member.reserve(book6));
    }

    @Test
    void shouldNotReserveSameBookMoreThanOnce() {
        book.setStatus(BookStatus.CHECKED_OUT);
        member.reserve(book);
        member.reserve(book);

        assertEquals(Set.of(book), member.getReservedBooks());
    }
}