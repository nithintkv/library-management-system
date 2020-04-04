package acumen.library;

public class Book {
    private BookDetail bookDetail;
    private String uid;
    private BookStatus status;
    private Member borrower;
    private Member reserver;

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

    public BookStatus getStatus() {
        return status;
    }

    public String getUid() {
        return uid;
    }

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void loanedBy(Member member) {
        borrower = member;
    }

    public Member getBorrower() {
        return borrower;
    }

    public void reservedBy(Member member) {
        reserver = member;
    }

    public Member getReserver() {
        return reserver;
    }
}
