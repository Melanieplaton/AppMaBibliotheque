package com.example.mlanie.mabibliotheque;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Mélanie on 29/09/2016.
 */
@RunWith(AndroidJUnit4.class)
public class BookDisplayEspressoTest {
    @Rule
    public IntentsTestRule<Book_Display> mActivityRule = new IntentsTestRule<>(Book_Display.class);

    @Test
    public void testModifyButtonClicked() {
        /*Intent resultData = new Intent();
        resultData.putExtra("BookID", 1);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(toPackage("com.example.mlanie.mabibliotheque.Book_Display")).respondWith(result);*/

        //Test to check the attributes of book from BookId. To show the book display, we need a bookId. We try to use intending but it did not work.
        intending(allOf(
                hasExtra("BookID", 1),
                toPackage("com.example.mlanie.mabibliotheque")));

        onView(withId(R.id.activityBookDisplay));
        onView(withId(R.id.modifyButton)).perform(click());

        intended(allOf(
                hasExtra("Book to modify", 1),
                toPackage("com.example.mlanie.mabibliotheque")));
    }
}
