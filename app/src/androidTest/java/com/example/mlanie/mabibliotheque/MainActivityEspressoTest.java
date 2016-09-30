package com.example.mlanie.mabibliotheque;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


/**
 * Created by Mélanie on 29/09/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<MainActivity>(MainActivity.class);
    @Test
    public void testGoToRegistrationBookWhenButtonClicked () {
        onView(withId(R.id.button)).perform(click());
        intended(toPackage("com.example.mlanie.mabibliotheque"));
    }

    @Test
    public void testGoToBookDetails(){
        //test when click on the first item of the list
        onView(withId(R.id.liste_main));
        onData(anything()).atPosition(1);
        onData(anything()).perform(click());
        intended(allOf(
                hasExtra("BookID", 1),
                toPackage("com.example.mlanie.mabibliotheque")));
    }
}
