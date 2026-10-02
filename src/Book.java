public class Book {
    String title;
    String author;
    int year;
    private boolean available;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public void borrowBook(){
        this.available = false;
    }

    @Override
    public String toString() {
        String availableText;
        if (this.available = false) {
        availableText = "not available";

     }
        else {
            availableText = "available";
        }
        return this.title + " | " + this.author + " | " + this.year + " | " + availableText;

    }

    public void returnBook(){
        this.available = true;
    }



}
