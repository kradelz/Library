import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    static ArrayList<Book> findBook(String searchedString, ArrayList<Book> books) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for (Book comparedBook : books) {
            if (comparedBook.title.equals(searchedString) || comparedBook.author.equals(searchedString)) {
                foundBooks.add(comparedBook);
            }
        }
        return foundBooks;
    }

    static Optional<Book> findAndPickOneBook(String searchedString, ArrayList<Book> books) {
        ArrayList<Book> possibleBooks = findBook(searchedString, books);

        // skip choosing a book
        if (possibleBooks.size() == 1) {
            return Optional.of(possibleBooks.get(0));
        }

        System.out.println("Please pick which book you want. To pick a book you need to enter in its index. Giving -1 exits the search.");
        for (int i=0; i<possibleBooks.size(); i++) {
            Book b = possibleBooks.get(i);
            System.out.printf("%d %s", i + 1, b);  // humans use 1 based index.
        }
        System.out.print("Enter in index: ");

        int index = scanner.nextInt();
        scanner.nextLine();  // consume \n
        if (index == -1) {
            return Optional.empty();
        }

        index -= 1;
        try {
            Book b = possibleBooks.get(index);
            return Optional.of(b);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Atesten Gomlek","Halide Edip Adıvar",1922));
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

            // this is needed because nextInt() does not consume the newline char in input buffer
            scanner.nextLine();

            if (action == 1) {
                String newTitle;
                String newAuthor;
                int newYear;

                System.out.println("Please enter book's title : ");
                newTitle = scanner.nextLine();
                System.out.println("Please enter book's author : ");
                newAuthor = scanner.nextLine();
                System.out.println("Please enter book's publishing year : ");
                newYear = scanner.nextInt();
                books.add(new Book(newTitle,newAuthor,newYear));
            } else if (action == 2) {
                for (Book book : books) {
                    System.out.println(book.toString());
                }
            }
            else if (action == 3) {  // search
                System.out.print("Please put in a book title/author: ");
                String bookName = scanner.nextLine().trim();
                Optional<Book> optionalBook = findAndPickOneBook(bookName, books);
                if (optionalBook.isPresent()) {
                    Book b = optionalBook.get();
                    System.out.println("Found book.");
                    System.out.println(b);
                } else {
                    System.out.println("Could not find book.");
                }
            } else if (action == 4) {
                System.out.print("Please put in a book title/author: ");
                String bookName = scanner.nextLine().trim();
                Optional<Book> optionalBook = findAndPickOneBook(bookName, books);
                if (optionalBook.isPresent())  {
                    Book b = optionalBook.get();
                    System.out.printf("Found the book: %s\n", b);
                    if (b.isAvailable()) {
                        System.out.println("Book is available. Borrowing...");
                        b.borrowBook();
                    } else {
                        System.out.println("Unfortunately book is not available.");
                    }
                }else {
                    System.out.println("Book cannot be found.");
                }
            } else if (action == 5) {
                System.out.print("Please put in a book title/author: ");
                String bookName = scanner.nextLine().trim();
                Optional<Book> optionalBook = findAndPickOneBook(bookName, books);

                if (optionalBook.isPresent()) {
                    Book b = optionalBook.get();
                    System.out.printf("Found the book: %s\n", b);
                    if (!b.isAvailable()) {
                        b.returnBook();
                        System.out.println("Book was successfully returned.");
                    }
                    else {
                        System.out.println("Book is already available. Cannot be returned.");
                    }
                } else {
                    System.out.println("Book cannot be found.");
                }
            } else if (action == 6) {
                break;
            } else {
                System.out.println("Invalid action.");
            }
        }

    }
}