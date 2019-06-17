package com.dbse.android.spendemon;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;


public class EditDataTest {

    @Rule
    public ActivityTestRule<EditData> mActivityTestRule = new ActivityTestRule<>(EditData.class);

    private EditData mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchViews() {
        View spinner = mActivity.findViewById(R.id.sCategory);
        assertNotNull(spinner);
        spinner = mActivity.findViewById(R.id.sPaymentMethod);
        assertNotNull(spinner);
        spinner = mActivity.findViewById(R.id.sType);
        assertNotNull(spinner);
        View editText = mActivity.findViewById(R.id.etDate);
        assertNotNull(editText);
        editText = mActivity.findViewById(R.id.etAmount);
        assertNotNull(editText);
        editText = mActivity.findViewById(R.id.etDescription);
        assertNotNull(editText);
    }

    @Test
    public void dataIncomeEntryTest() {
        String type = "Incomes";
        String paymentMethod = "GooglePay";
        String category = "Salary";
        String amount = "750";
        String notes = "This is a test addition";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = "" + day + "/" + (month + 1) + "/" + year;

        Intents.init();
        Espresso.onView(withId(R.id.sType)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(type)))
                .perform(click());
        Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText(containsString(type))));
        Espresso.onView(withId(R.id.sCategory)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(category)))
                .perform(click());
        Espresso.onView(withId(R.id.sPaymentMethod)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(paymentMethod)))
                .perform(click());
        Espresso.onView(withId(R.id.sPaymentMethod)).check(matches(withSpinnerText(paymentMethod)));

        Espresso.onView(withId(R.id.sCategory)).check(matches(withSpinnerText(category)));
        Espresso.onView(withId(R.id.etAmount)).perform(ViewActions.typeText(amount));
        Espresso.onView(withId(R.id.etDescription)).perform(ViewActions.typeText(notes));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.save_table)).perform(click());
        intended(hasComponent(Summary.class.getName()));
        Espresso.onView(withId(R.id.rvEntries))
                .perform(RecyclerViewActions
                        .<RecyclerView.ViewHolder>actionOnItemAtPosition(0, click()));
        intended(hasComponent(DetailsActivity.class.getName()));
        Espresso.onView(withId(R.id.tvAmountDetails))
                .check(matches(withText(containsString(amount))));
        Espresso.onView(withId(R.id.tvNotesDetails))
                .check(matches(withText(containsString(notes))));
        Espresso.onView(withId(R.id.tvDateDetails))
                .check(matches(withText(containsString(date))));

        Intents.release();
    }

    @Test
    public void dataExpenseEntryTest() {
        String type = "Expenses";
        String paymentMethod = "Card";
        String category = "Restaurant";
        String amount = "630";
        String notes = "This is a test Expense addition";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = "" + day + "/" + (month + 1) + "/" + year;

        Intents.init();
        Espresso.onView(withId(R.id.sType)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(type)))
                .perform(click());
        Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText(containsString(type))));
        Espresso.onView(withId(R.id.sCategory)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(category)))
                .perform(click());
        Espresso.onView(withId(R.id.sPaymentMethod)).perform(click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(paymentMethod)))
                .perform(click());
        Espresso.onView(withId(R.id.sPaymentMethod)).check(matches(withSpinnerText(paymentMethod)));

        Espresso.onView(withId(R.id.sCategory)).check(matches(withSpinnerText(category)));
        Espresso.onView(withId(R.id.etAmount)).perform(ViewActions.typeText(amount));
        Espresso.onView(withId(R.id.etDescription)).perform(ViewActions.typeText(notes));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.save_table)).perform(click());
        intended(hasComponent(Summary.class.getName()));
        Espresso.onView(withId(R.id.rvEntries))
                .perform(RecyclerViewActions
                        .<RecyclerView.ViewHolder>actionOnItemAtPosition(0, click()));
        intended(hasComponent(DetailsActivity.class.getName()));
        Espresso.onView(withId(R.id.tvAmountDetails))
                .check(matches(withText(containsString(amount))));
        Espresso.onView(withId(R.id.tvNotesDetails))
                .check(matches(withText(containsString(notes))));
        Espresso.onView(withId(R.id.tvDateDetails))
                .check(matches(withText(containsString(date))));

        Intents.release();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}