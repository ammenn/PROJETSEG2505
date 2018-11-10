package com.example.rayold.everydayneeds;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class EditTest {
    /*Pour que ce test marche il faudrait déjà avoir ajouter un service .
     * Nous on a choisi 'Demenagement' dont le prix etait au depart '100* $/*/
    @Rule
    public ActivityTestRule<admin> mActivityTestRule = new
            ActivityTestRule<>(admin.class);
    @Test
    public void editservice() {


        onView(withId(R.id.editService)).perform(typeText("Demenagement"), closeSoftKeyboard());
        onView(withId(R.id.editHourlyPrice)).perform(typeText("50"), closeSoftKeyboard());


        onView(withId(R.id.buttonEdit)).perform(click());
        onView(withText("Unsuccesfull add")).check(matches(isDisplayed()));
    }
}
