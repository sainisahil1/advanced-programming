package io.sahil.assignment3.library;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import java.util.ArrayList;

/**
 * This class represent University Library
 * @author Sahil Saini
 */
public class Library extends ConsoleProgram {

    ArrayList<Book> books;
    ArrayList<Student> students;
    RandomGenerator generator = new RandomGenerator();

    public static void main(String[] args) {
        Library library = new Library();
        library.start();
    }

    public void init() {
        setSize(400, 600);
        setFont("Courier-14");
        addSampleBooks();
        addSampleStudents();
        println("Welcome to the Library!");
        println("0: Create new Student\n" +
                "1: Create new book\n" +
                "2: Display All Students\n" +
                "3: Display All Books\n" +
                "4: Return a book");
    }

    public void run() {
        while (true) {
            int choice = readInt("Your choice: ");
            switch (choice) {
                case 0:
                    createNewStudent();
                    break;
                case 1:
                    createNewBook();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    displayAllBooks();
                    break;
                case 4:
                    returnRandomBookFromRandomStudent();
                    break;
            }
        }
    }

    private void addSampleBooks() {
        books = new ArrayList<>();
        Book book1 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari");
        Book book2 = new Book("1984", "George Orwell");
        Book book3 = new Book("The Alchemist", "Paulo Coelho");
        Book book4 = new Book("Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones", "James Clear");
        Book book5 = new Book("To Kill a Mockingbird", "Harper Lee");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    private void addSampleStudents() {
        students = new ArrayList<>();
        Student student1 = new Student("Monica Geller", "MG123");
        student1.borrowBook(books.get(generator.nextInt(books.size())));
        Student student2 = new Student("Chandler Bing", "CB113");
        student2.borrowBook(books.get(generator.nextInt(books.size())));
        Student student3 = new Student("Ross Geller", "RG122");
        student3.borrowBook(books.get(generator.nextInt(books.size())));
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    private void displayAllStudents() {
        println("All students: "+students);
    }

    private void displayAllBooks() {
        println("All books: "+books);
    }

    /**
     * Picks a random book from a random student's borrowed book list
     */
    private void returnRandomBookFromRandomStudent() {
        int studentIndex = generator.nextInt(students.size());
        Student student = students.get(studentIndex);
        ArrayList<Book> borrowedBooks = student.getBorrowedBooks();
        int randomBookIndex = generator.nextInt(borrowedBooks.size());
        Book randomBook = borrowedBooks.get(randomBookIndex);
        student.returnBook(randomBook);
        println("Book returned: "+randomBook + " by Student: "+student);
    }

    /**
     * Prompt user to create new student
     */
    private void createNewStudent() {
        String name = readLine("Student name: ");
        String studentId = readLine("Student id: ");
        Student newStudent = new Student(name, studentId);
        students.add(newStudent);
    }

    /**
     * Prompt user to create new book
     */
    private void createNewBook() {
        String name = readLine("Book name: ");
        String author = readLine("Author: ");
        Book newBook = new Book(name, author);
        books.add(newBook);
    }

}
