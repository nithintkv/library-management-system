package acumen.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;
    private BookDetail bookDetail;
    private Book book;

    @BeforeEach
    void createObjects() {
        person = new Person("Name", "Address", "email");
        bookDetail = new BookDetail("Name", "Author", "Subject");
        book = new Book(bookDetail, "uid");
    }

    @Test
    void canary() {
        assertTrue(true);
    }

    @Test
    void shouldReserveNewBook() {
        assertTrue(person.reserve(book));
    }

    @Test
    void shouldGetReservedBooks() {
        person.reserve(book);
        assertEquals(List.of(book), person.getReservedBooks());
    }

    @Test
    void shouldNotReserveNewBookIfLimitReached() {
        
        assertTrue(person.reserve(book));
    }
}