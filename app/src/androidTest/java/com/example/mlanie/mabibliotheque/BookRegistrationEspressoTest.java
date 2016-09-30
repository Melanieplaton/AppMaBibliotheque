package com.example.mlanie.mabibliotheque;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by Mélanie on 29/09/2016.
 */
@RunWith(AndroidJUnit4.class)
public class BookRegistrationEspressoTest {
    @Rule
    public IntentsTestRule<BookRegistration> mActivityRule = new IntentsTestRule<BookRegistration>(BookRegistration.class);

    //Two tests are needed depending on a new book or a book to modify
    @Test
    public void testRegistrationBookWhenButtonClicked(){
        //We need to fill the all edittext with data from a book
        int idBookToModify = -1;
        onView(withId(R.id.saveBook)).perform(click());
        intended(toPackage("com.example.mlanie.mabibliotheque"));
        /*intended(allOf(
                hasExtra("Book to modify", 1),
                toPackage("com.example.mlanie.mabibliotheque")));*/
    }
}
