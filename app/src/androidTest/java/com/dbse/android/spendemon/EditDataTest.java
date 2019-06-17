package com.dbse.android.spendemon;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
    public void dataEntryTest() {
        String type = "Incomes";
        String paymentMethod = "GooglePay";
        String category = "Salary";
        Espresso.onView(withId(R.id.sType)).perform(ViewActions.click());
        Espresso.onData(allOf(is(instanceOf(String.class)), is(type)))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText(containsString(type))));
        Espresso.onView(withId(R.id.sPaymentMethod)).perform(ViewActions.click());
        Espresso.onData(allOf(is(instanceOf(String.class)),is(paymentMethod)))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.sPaymentMethod)).check(matches(withSpinnerText(paymentMethod)));
        Espresso.onView(withId(R.id.sCategory)).perform(ViewActions.click());
        Espresso.onData(allOf(is(instanceOf(String.class)),is(category)))
                .perform(ViewActions.click());
        Espresso.onView(withId(R.id.sCategory)).check(matches(withSpinnerText(category)));
        //Espresso.onView(withId(R.id.etDate)).perform(ViewActions.click());
        //Espresso.onView(withId(R.id.etDate)).perform(PickerActions.setDate(2017,6,17));
        Espresso.onView(withId(R.id.etAmount)).perform(ViewActions.typeText("750"));



    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}