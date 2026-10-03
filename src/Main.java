import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    static ArrayList<Book> findBook(String searchedString, ArrayList<Book> books) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        searchedString = searchedString.toLowerCase();

        for (Book comparedBook : books) {
            if (comparedBook.getTitle().toLowerCase().contains(searchedString) || comparedBook.getAuthor().toLowerCase().contains(searchedString)) {
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
        } else if (possibleBooks.isEmpty()) {
            return Optional.empty();
        }

        System.out.println("Please pick which book you want. To pick a book you need to enter in its index. Giving -1 exits the search.");
        for (int i=0; i<possibleBooks.size(); i++) {
            Book b = possibleBooks.get(i);
            System.out.printf("%d %s%n", i + 1, b);  // humans use 1 based index.
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

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Atesten Gomlek","Halide Edip Adıvar",1922));
        books.add(new Book("Art of War","Sun Tzu", -500));

        boolean running = true;
        while (running) {
            System.out.println("1. Add a book");
            System.out.println("2. Show all books");
            System.out.println("3. Search for a book");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.println("Please enter in an action : ");

            int action;
            try {
                action = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer.");
                continue;
            }

            switch (action) {
                case 1: {
                    String newTitle;
                    String newAuthor;
                    int newYear;

                    System.out.println("Please enter book's title : ");
                    newTitle = scanner.nextLine();
                    System.out.println("Please enter book's author : ");
                    newAuthor = scanner.nextLine();
                    System.out.println("Please enter book's publishing year : ");
                    newYear = scanner.nextInt();
                    scanner.nextLine();
                    books.add(new Book(newTitle,newAuthor,newYear));
                    break;
                }
                case 2: {
                    for (Book book : books) {
                        System.out.println(book);
                    }
                    break;
                }
                case 3: {
                    System.out.print("Please put in a book title/author: ");
                    String bookName = scanner.nextLine().trim();
                    ArrayList<Book> foundBooks = findBook(bookName, books);

                    if (foundBooks.isEmpty()) System.out.println("Could not find book.");
                    System.out.println("Found these books matching the entered string:");
                    for (Book b : foundBooks) {
                        System.out.println(b);
                    }
                    break;
                }
                case 4: {
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
                    break;
                }
                case 5: {
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
                    break;
                }
                case 6: {
                    running = false;
                    break;
                }
                default: {
                    System.out.println("Invalid action.");
                }
            }
        }

    }
}