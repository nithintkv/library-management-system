package acumen.library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Member {
    private String username;
    private String name;
    private String address;
    private String email;
    private List<Book> reservedBooks;
    private List<Book> checkedOutBooks;

    public Member(String name, String username, String address, String email) {
        this.email = email;
        this.address = address;
        this.name = name;
        this.username = username;
        reservedBooks = new ArrayList<>();
        checkedOutBooks = new ArrayList<>();
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void addReservedBooks(Book book) {
        reservedBooks.add(book);
    }

    public void addCheckedOutBooks(Book book) {
        checkedOutBooks.add(book);
    }

    public void removeCheckedOutBook(Book book) {
        checkedOutBooks.remove(book);
    }

    public void removeReservedBook(Book book) {
        reservedBooks.remove(book);
    }
}
