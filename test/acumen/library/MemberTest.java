package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldAddToReservedBooks() {
        member.addReservedBooks(book);

        assertEquals(List.of(book), member.getReservedBooks());
    }

    @Test
    void shouldAddToCheckedOutBooks() {
        member.addCheckedOutBooks(book);

        assertEquals(List.of(book), member.getCheckedOutBooks());
    }

    @Test
    void shouldRemoveFromCheckedOutBooks() {
        member.addCheckedOutBooks(book);
        assertEquals(List.of(book), member.getCheckedOutBooks());

        member.removeCheckedOutBook(book);
        assertEquals(List.of(), member.getCheckedOutBooks());
    }

    @Test
    void shouldRemoveFromReservedBooks() {
        member.addReservedBooks(book);
        assertEquals(List.of(book), member.getReservedBooks());

        member.removeReservedBook(book);
        assertEquals(List.of(), member.getReservedBooks());
    }
}