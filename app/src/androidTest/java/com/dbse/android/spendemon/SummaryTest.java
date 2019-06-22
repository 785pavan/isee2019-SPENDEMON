package com.dbse.android.spendemon;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.junit.Assert.assertNotNull;


public class SummaryTest extends IntentsTestRule {

    @Rule
    public ActivityTestRule<Summary> mActivityTestRule = new ActivityTestRule<>(Summary.class);

    private Summary mSummary = null;

    public SummaryTest() {
        super(Summary.class);
    }

    @Before
    public void setUp() throws Exception {
        mSummary = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchViews() {
        View recyclerView = mSummary.findViewById(R.id.rvEntries);
        View drawerView = mSummary.findViewById(R.id.drawer_layout_sum);
        View navBar = mSummary.findViewById(R.id.navigation_view1_Sum);
        FloatingActionButton fab = mSummary.findViewById(R.id.fabPlus);

        assertNotNull(fab);
        fab = mSummary.findViewById(R.id.fabMinus);
        assertNotNull(fab);
        assertNotNull(navBar);
        navBar = mSummary.findViewById(R.id.navigation_view2_Sum);
        assertNotNull(navBar);
        assertNotNull(drawerView);
        assertNotNull(recyclerView);
    }

    @Test
    public void minusClickTest() {
        Intents.init();
        Espresso.onView(withId(R.id.fabMinus)).perform(ViewActions.click());
        intended(hasComponent(EditData.class.getName()));
        Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText("Expenses")));
        Intents.release();
    }

    @Test
    public void plusClickTest() {
        Intents.init();
        Espresso.onView(withId(R.id.fabPlus)).perform(ViewActions.click());
        intended(hasComponent(EditData.class.getName()));
        Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText("Incomes")));
        Intents.release();
    }

    @Test
    public void detailsClickTest() {
        Intents.init();
        Espresso.onView(withId(R.id.rvEntries)).perform(ViewActions.click());
        intended(hasComponent(DetailsActivity.class.getName()));
        Intents.release();
    }


    @After
    public void tearDown() throws Exception {
        mSummary = null;
    }
}