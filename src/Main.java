import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Atesten Gomlek","Halide Edip Adıvar",1919));
        books.add(new Book("Art of War","Sun Tzu", -500));


        System.out.println("1. Add a book");
        System.out.println("2. Show all books");
        System.out.println("3. Search for a book");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Exit");
        System.out.println("Please enter in an action : ");
        int action = scanner.nextInt();

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
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);

                System.out.println(book.toString());
            }

        }

    }
}