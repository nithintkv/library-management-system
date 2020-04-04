package acumen.library;

public class BookDetail {
    private String name;
    private String author;
    private String subject;

    public BookDetail(String name, String author, String subject) {
        this.name = name;
        this.author = author;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
