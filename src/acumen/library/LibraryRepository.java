package acumen.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryRepository implements Repository {
    private Map<String, List<Book>> bookNames;
    private Map<String, List<Book>> bookAuthors;
    private Map<String, Book> bookUids;

    public LibraryRepository() {
        bookNames = new HashMap<>();
        bookAuthors = new HashMap<>();
        bookUids = new HashMap<>();
    }

    @Override
    public List<Book> searchByName(String name) {
        return bookNames.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return bookAuthors.getOrDefault(author, new ArrayList<>());
    }

    @Override
    public Book searchById(String uid) {
        return bookUids.getOrDefault(uid, null);
    }

    @Override
    public void addBook(Book book) {
        List<Book> books = searchByName(book.getBookDetail().getName());
        books.add(book);
        bookNames.put(book.getBookDetail().getName(), books);

        books = searchByAuthor(book.getBookDetail().getAuthor());
        books.add(book);
        bookAuthors.put(book.getBookDetail().getAuthor(), books);

        bookUids.put(book.getUid(), book);
    }
}
