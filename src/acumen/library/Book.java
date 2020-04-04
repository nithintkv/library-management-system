package acumen.library;

public class Book {
    private BookDetail bookDetail;
    private String uid;
    private BookStatus status;

    public Book(BookDetail bookDetail, String uid) {
        this.bookDetail = bookDetail;
        this.uid = uid;
        this.status = BookStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return BookStatus.AVAILABLE.equals(status);
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public String getAuthor() {
        return bookDetail.getAuthor();
    }

    public String getName() {
        return bookDetail.getName();
    }
}
