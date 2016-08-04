package com.example.mlanie.mabibliotheque;

import android.os.Parcelable;

/**
 * Created by Mélanie on 03/08/2016.
 */
public class Book {
    String bookTitle;
    String authorName;
    int publishedYear;
    private int bookId;

    public final String bookIdentifier = "BOOK_ID";

    public Book(String bookTitle, String authorName, int publishedYear) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookTitle='" + bookTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", publishedYear=" + publishedYear +
                '}';
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
