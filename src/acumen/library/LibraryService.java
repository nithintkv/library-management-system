package acumen.library;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private static final int MAX_CHECK_OUT_LIMIT = 5;
    private Repository libraryRepo;

    public void setLibraryRepo(Repository libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    private boolean isBookDetailPresent(String name) {
        return libraryRepo.searchByName(name).size() > 0;
    }

    private boolean isLimitReachedFor(Member member) {
        return member.getCheckedOutBooks().size() == MAX_CHECK_OUT_LIMIT;
    }

    public void addBook(String name, String author, String subject, String uid) {
        BookDetail bookDetail;
        if (isBookDetailPresent(name))
            bookDetail = libraryRepo.searchByName(name).get(0).getBookDetail();
        else
            bookDetail = new BookDetail(name, author, subject);
        libraryRepo.addBook(new Book(bookDetail, uid));
    }

    public boolean checkout(Member member, Book book) {
        if (!book.isAvailable()) return false;
        if (isLimitReachedFor(member)) return false;

        member.addCheckedOutBooks(book);
        book.setStatus(BookStatus.CHECKED_OUT);
        book.loanedBy(member);

        return true;

    }

    public boolean reserve(Member member, Book book) {
        if (book.isAvailable()) return false;
        if (book.getStatus().equals(BookStatus.RESERVED)) return false;
        if (book.getStatus().equals(BookStatus.CHECKED_OUT) &&
            book.getBorrower().equals(member)) return false;

        member.addReservedBooks(book);
        book.setStatus(BookStatus.RESERVED);
        book.reservedBy(member);

        return true;
    }

    public List<Book> getBooksCheckedOutBy(Member member) {
        return new ArrayList<>(member.getCheckedOutBooks());
    }

    public List<Book> getBooksReservedBy(Member member) {
        return new ArrayList<>(member.getReservedBooks());
    }

    public boolean returnBook(Book book) {
        if (book.isAvailable()) return false;
        Member member = book.getBorrower();
        member.removeCheckedOutBook(book);

        if (!book.getStatus().equals(BookStatus.RESERVED))
            book.setStatus(BookStatus.AVAILABLE);

        book.loanedBy(null);
        return true;
    }
}
