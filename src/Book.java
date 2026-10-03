public class Book {
    private String title;
    private String author;
    private int year;
    private boolean borrowed;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowed = false;
    }

    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public int getYear() {
        return this.year;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYear(int year) {
        this.year = year;
    }


    public boolean isAvailable() {
        return !this.borrowed;
    }

    public void borrowBook(){
        this.borrowed = true;
    }

    @Override
    public String toString() {
        String availableText;
        if (this.borrowed) {
            availableText = "Borrowed";
        }
        else {
            availableText = "Available";
        }
        return this.title + " | " + this.author + " | " + this.year + " | " + availableText;

    }

    public void returnBook(){
        this.borrowed = false;
    }



}
