package com.kedrad.hoptest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by rad on 16.05.18.
 */
@RunWith(AndroidJUnit4.class)
public class CalculatingTests {

    @Rule
    public ActivityTestRule<MainActivity> mSingleLegHopForDistanceTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCalculatingAndShowingResults() throws Exception {
        float opLeg1 = 3;
        float opLeg2 = 2;
        float nopLeg1 = 4;
        float nopLeg2 = 6;
        //Calculating average and the result
        float opLegAverage = (opLeg1 + opLeg2) / 2;
        float nopLegAverage = (nopLeg1 + nopLeg2) / 2;

        float result = opLegAverage / nopLegAverage;

        // Add all of the four measurements
        onView(allOf(withId(R.id.editTextOp1), isCompletelyDisplayed())).perform(typeText(Float.toString(opLeg1)), closeSoftKeyboard());
        onView(allOf(withId(R.id.editTextOp2), isCompletelyDisplayed())).perform(typeText(Float.toString(opLeg2)), closeSoftKeyboard());
        onView(allOf(withId(R.id.editTextNop1), isCompletelyDisplayed())).perform(typeText(Float.toString(nopLeg1)), closeSoftKeyboard());
        onView(allOf(withId(R.id.editTextNop2), isCompletelyDisplayed())).perform(typeText(Float.toString(nopLeg2)), closeSoftKeyboard());

        // Submit the measurements
        onView(withId(R.id.fab)).perform(click());

        // Verify that the result is displayed
        onView(allOf(withId(R.id.textViewResult), isCompletelyDisplayed())).check(matches(withText(containsString(Float.toString(result)))));
    }
}
