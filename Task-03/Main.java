import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author, boolean isIssued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void setIssued(boolean issued) { isIssued = issued; }

    public String toFileString() {
        return id + "," + title + "," + author + "," + isIssued;
    }

    public static Book fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return null;
        return new Book(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Status: " + (isIssued ? "Issued" : "Available");
    }
}

public class Main {
    private static final String FILE_NAME = "library_books.txt";
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadBooksFromFile();

        while (true) {
            System.out.println("\nFILE-BASED LIBRARY MANAGEMENT SYSTEM");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: searchBook(); break;
                case 4: issueBook(); break;
                case 5: returnBook(); break;
                case 6:
                    saveBooksToFile();
                    System.out.println("Data saved. Exiting system!");
                    return;
                default:
                    System.out.println("Invalid option. Choose between 1 and 6.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();

        books.add(new Book(id, title, author, false));
        saveBooksToFile();
        System.out.println("Book added and saved successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library database.");
            return;
        }
        System.out.println("\nLibrary Collection:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void searchBook() {
        System.out.print("Enter Title or Author to search: ");
        String query = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(query) || b.getAuthor().toLowerCase().contains(query)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String id = scanner.nextLine().trim();

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                if (b.isIssued()) {
                    System.out.println("Book is already issued!");
                } else {
                    b.setIssued(true);
                    saveBooksToFile();
                    System.out.println("Book issued successfully!");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String id = scanner.nextLine().trim();

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                if (!b.isIssued()) {
                    System.out.println("This book was not issued.");
                } else {
                    b.setIssued(false);
                    saveBooksToFile();
                    System.out.println("Book returned successfully!");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    private static void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                writer.println(b.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    private static void loadBooksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book b = Book.fromFileString(line);
                if (b != null) books.add(b);
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }
}
