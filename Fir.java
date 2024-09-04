import java.util.*;

class Fir {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome to the Library");

        // Creating an object for the Library class
        Library library = new Library();

        // Adding some initial books to the library
        Book book1 = new Book("AAA", "BBB", 111);
        Book book2 = new Book("CCC", "DDD", 222);
        Book book3 = new Book("EEE", "FFF", 333);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Loop to keep the menu active until the user chooses to exit
        while (true) {
            boolean exit = false;

            // Displaying the menu to the user
            System.out.println("\n1: Add Book");
            System.out.println("2: Remove Book");
            System.out.println("3: Search Book");
            System.out.println("4: Loan Book");
            System.out.println("5: Return Book");
            System.out.println("6: Exit");
            System.out.print("Choose an operation: ");
            int choice = sc.nextInt();

            // Switch case to perform the operation based on the user's choice
            switch (choice) {
                case 1:
                    // Adding a new book to the library
                    System.out.print("Enter title, author, ISBN of the book: ");
                    String title = sc.next();
                    String author = sc.next();
                    int isbn = sc.nextInt();
                    Book newBook = new Book(title, author, isbn);
                    library.addBook(newBook);
                    break;
                case 2:
                    // Removing a book from the library
                    library.removeBook();
                    break;
                case 3:
                    // Searching for a book in the library
                    System.out.println("Enter 1 for Search by Title, 2 for Search by Author, 3 for Search by ISBN: ");
                    int searchOption = sc.nextInt();
                    if (searchOption == 1)
                        library.searchByTitle();
                    else if (searchOption == 2)
                        library.searchByAuthor();
                    else
                        library.searchByISBN();
                    break;
                case 4:
                    // Loaning a book
                    library.loanBook();
                    break;
                case 5:
                    // Returning a book
                    library.returnBook();
                    break;
                case 6:
                    // Exiting the program
                    library.updateCatalog();
                    exit = true;
                    break;
            }
            if (exit) break;
        }
    }
}

// Class representing a node in the linked list
class Node {
    Book book;
    Node next;

    public Node(Book book, Node next) {
        this.book = book;
        this.next = next;
    }
}

// Class representing a book with title, author, ISBN, and availability status
class Book {
    String title;
    String author;
    int isbn;
    boolean isAvailable;

    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }
}

// Class representing the library with methods to manage books
class Library {
    static Node bookList = new Node(new Book("Dummy", "SV", 4585), null);
    static Node currentBookNode = bookList;
    static Queue<Book> removalRequests = new LinkedList<>();
    static Stack<Book> recentAdditions = new Stack<>();

    // Method to add a book to the library
    public static void addBook(Book book) {
        currentBookNode.next = new Node(book, null);
        currentBookNode = currentBookNode.next;
        recentAdditions.push(book);
    }

    // Method to remove a book from the library
    public static void removeBook() {
        Scanner sc = new Scanner(System.in);

        // Prompting user to choose removal by title or ISBN
        System.out.println("Enter 1 to Remove Book by Title or 2 to Remove Book by ISBN: ");
        int option = sc.nextInt();
        boolean bookExists = false;

        if (option == 1) {
            System.out.print("Enter the title of the book: ");
            String title = sc.next();
            Node temp = bookList.next;
            while (temp != null) {
                Book book = temp.book;
                if (book.title.equals(title)) {
                    removalRequests.add(book);
                    bookExists = true;
                    break;
                }
                temp = temp.next;
            }
        } else {
            System.out.print("Enter the ISBN of the book: ");
            int isbn = sc.nextInt();
            Node temp = bookList.next;
            while (temp != null) {
                Book book = temp.book;
                if (book.isbn == isbn) {
                    removalRequests.add(book);
                    bookExists = true;
                    break;
                }
                temp = temp.next;
            }
        }

        if (!bookExists)
            System.out.println("Book doesn't exist.");
        else
            System.out.println("Operation successful.");
    }

    // Method to search for a book by title
    public static void searchByTitle() {
        System.out.print("Enter the title of the book: ");
        Scanner sc = new Scanner(System.in);
        String title = sc.next();
        int status = 0;

        Node temp = bookList.next;
        while (temp != null) {
            Book book = temp.book;
            if (book.title.equals(title)) {
                if (book.isAvailable)
                    status = 1;
                else
                    status = 2;
                break;
            }
            temp = temp.next;
        }

        if (status == 1)
            System.out.println("Book exists and is available.");
        else if (status == 2)
            System.out.println("Book is on loan.");
        else
            System.out.println("Book does not exist.");
    }

    // Method to search for a book by author
    public static void searchByAuthor() {
        System.out.print("Enter the author of the book: ");
        Scanner sc = new Scanner(System.in);
        String author = sc.next();
        int status = 0;

        Node temp = bookList.next;
        while (temp != null) {
            Book book = temp.book;
            if (book.author.equals(author)) {
                if (book.isAvailable)
                    status = 1;
                else
                    status = 2;
                break;
            }
            temp = temp.next;
        }

        if (status == 1)
            System.out.println("Book exists and is available.");
        else if (status == 2)
            System.out.println("Book is on loan.");
        else
            System.out.println("Book does not exist.");
    }

    // Method to search for a book by ISBN
    public static void searchByISBN() {
        System.out.print("Enter the ISBN of the book: ");
        Scanner sc = new Scanner(System.in);
        int isbn = sc.nextInt();
        int status = 0;

        Node temp = bookList.next;
        while (temp != null) {
            Book book = temp.book;
            if (book.isbn == isbn) {
                if (book.isAvailable)
                    status = 1;
                else
                    status = 2;
                break;
            }
            temp = temp.next;
        }

        if (status == 1)
            System.out.println("Book exists and is available.");
        else if (status == 2)
            System.out.println("Book is on loan.");
        else
            System.out.println("Book does not exist.");
    }

    // Method to loan a book from the library
    public static void loanBook() {
        System.out.print("Enter the ISBN of the book: ");
        Scanner sc = new Scanner(System.in);
        int isbn = sc.nextInt();
        Node temp = bookList.next;

        while (temp != null) {
            Book book = temp.book;
            if (book.isbn == isbn) {
                if (book.isAvailable) {
                    book.isAvailable = false;
                    System.out.println("Operation successful. The book is now on loan.");
                } else {
                    System.out.println("Book is already on loan.");
                }
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Method to return a book to the library
    public static void returnBook() {
        System.out.print("Enter the ISBN of the book: ");
        Scanner sc = new Scanner(System.in);
        int isbn = sc.nextInt();
        Node temp = bookList.next;

        while (temp != null) {
            Book book = temp.book;
            if (book.isbn == isbn) {
                book.isAvailable = true;
                System.out.println("Operation successful. The book is now returned.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Method to update the library catalog by removing books from the removal request queue
    public static void updateCatalog() {
        while (!removalRequests.isEmpty()) {
            Book bookToRemove = removalRequests.poll();
            Node temp = bookList;
            while (temp.next != null) {
                if (temp.next.book == bookToRemove) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
        System.out.println("All books data updated successfully.");
    }
}
