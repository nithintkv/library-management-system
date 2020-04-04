package acumen.library;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String address;
    private String email;
    private List<Book> reservedBooks;

    public Person(String name, String address, String email) {
        this.email = email;
        this.address = address;
        this.name = name;
        reservedBooks = new ArrayList<>();
    }

    public boolean reserve(Book book) {
        if (!book.isAvailable()) return false;
        reservedBooks.add(book);
        return true;
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }
}
