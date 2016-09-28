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
    private String authorFirstName;
    private String homeEdition;
    private Integer pages;
    private Float rating;
    private String review;
    private String bookSummary;
    private String bookType;


    public final String bookIdentifier = "BOOK_ID";

    public Book(String bookTitle, String authorName, int publishedYear, String authorFirstName, String homeEdition, int pages, Float rating, String review, String bookSummary, String bookType) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.publishedYear = publishedYear;
        this.authorFirstName = authorFirstName;
        this.homeEdition = homeEdition;
        this.pages = pages;
        this.rating = rating;
        this.review = review;
        this.bookSummary = bookSummary;
        this.bookType = bookType;
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

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getHomeEdition() {
        return homeEdition;
    }

    public void setHomeEdition(String homeEdition) {
        this.homeEdition = homeEdition;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
