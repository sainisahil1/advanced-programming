package io.sahil.assignment3.library;

import java.util.ArrayList;

/**
 * This class defines Student object
 *
 * @author Sahil Saini
 */
public class Student {

    private String name;
    private String id;
    private ArrayList<Book> borrowedBooks;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
