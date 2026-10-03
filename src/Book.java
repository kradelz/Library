public class Book {
    String title;
    String author;
    int year;
    private boolean borrowed;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowed = false;
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
            availableText = "unavailable";
        }
        else {
            availableText = "available";
        }
        return this.title + " | " + this.author + " | " + this.year + " | " + availableText;

    }

    public void returnBook(){
        this.borrowed = false;
    }



}
