package acumen.library;

import java.util.List;

public interface Repository {
    List<Book> searchByName(String name);
    List<Book> searchByAuthor(String author);
    Book searchById(String uid);
    void addBook(Book book);
}
