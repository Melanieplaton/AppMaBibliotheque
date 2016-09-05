package com.example.mlanie.mabibliotheque;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mélanie on 01/09/2016.
 */
public class BookRegistrationTest {
    @Test
    public void bookShouldBeFilledWithDataFromUser(){

        Book book = new Book();
        BookRegistration bookRegistration = new BookRegistration();
        bookRegistration.dataOfBook(book);
        bookRegistration.saveNewBookInBDD(book);

        Assert.assertNotNull(book);
    }


}
