import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Book findBook(String bookName, ArrayList<Book> books) {
        Book foundBook = new Book("invalid", "invalid", Integer.MIN_VALUE);

        for (Book comparedBook : books) {
            if (comparedBook.title.equals(bookName)) {
                // System.out.println("Found the book.");
                // System.out.println(comparedBook.toString());
                return comparedBook;
            }
        }
        return foundBook;
    }

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Atesten Gomlek","Halide Edip Adıvar",1919));
        books.add(new Book("Art of War","Sun Tzu", -500));


        while (true) {
            System.out.println("1. Add a book");
            System.out.println("2. Show all books");
            System.out.println("3. Search for a book");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.println("Please enter in an action : ");
            int action = scanner.nextInt();
            scanner.nextLine();  //

            if (action == 1) {
                String newTitle;
                String newAuthor;
                int newYear;

                System.out.println("Please enter book's title : ");
                newTitle = scanner.nextLine();
                System.out.println("...");
                System.out.println("Please enter book's author : ");
                newAuthor = scanner.nextLine();
                System.out.println("...");
                System.out.println("Please enter book's publishing year : ");
                newYear = scanner.nextInt();
                books.add(new Book(newTitle,newAuthor,newYear));
            } else if (action == 2) {
                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);

                    System.out.println(book.toString());
                }
            }
            else if (action == 3) {
                System.out.print("Please put in a book name: ");
                String bookName = scanner.nextLine().trim();
                Book b = findBook(bookName, books);
                if (b.year != Integer.MIN_VALUE) {
                    // found
                    System.out.println("Found book.");
                    System.out.println(b.toString());
                } else {
                    System.out.println("Could not find book.");
                }
            } else if (action == 4) {
                System.out.println("Enter in book to borrow");
                String bookName = scanner.nextLine().trim();
                Book b = findBook(bookName, books);
                if (b.isAvailable()) {
                    b.borrowBook();
                } else {
                    System.out.println("Book is not available unfortunately.");
                }
            } else if (action == 5) {
                System.out.println("Enter in book to return");
                String bookName = scanner.nextLine().trim();
                Book b = findBook(bookName, books);
                b.returnBook();
                System.out.println("Thanks for returning the book.");
            } else if (action == 6) {
                break;
            } else {
                System.out.println("Invalid action.");
            }
        }

    }
}