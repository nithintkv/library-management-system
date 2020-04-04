package acumen.library;

import java.util.HashSet;
import java.util.Set;

public class Member {
    private String username;
    private String name;
    private String address;
    private String email;
    private Set<Book> reservedBooks;

    public Member(String name, String username, String address, String email) {
        this.email = email;
        this.address = address;
        this.name = name;
        this.username = username;
        reservedBooks = new HashSet<>();
    }

    public boolean reserve(Book book) {
        if (reservedBooks.size() == 5 || book.isAvailable()) return false;
        reservedBooks.add(book);
        return true;
    }

    public Set<Book> getReservedBooks() {
        return reservedBooks;
    }
}
