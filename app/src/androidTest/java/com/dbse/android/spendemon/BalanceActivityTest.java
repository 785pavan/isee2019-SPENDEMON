package com.dbse.android.spendemon;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BalanceActivityTest extends IntentsTestRule {

    @Rule
    public ActivityTestRule<BalanceActivity> mActivityTestRule = new ActivityTestRule<>(BalanceActivity.class);

    private BalanceActivity mActivity = null;

    public BalanceActivityTest() {
        super(BalanceActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mActivity.findViewById(R.id.textView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.balanceTextView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.textViewIncomeValue);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.textViewExpenseValue);
        assertNotNull(view);
    }
    @Test
    public void balanceCheck() {
        Intents.init();
        onView(withId(R.id.drawer_layout_sum))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_balance));
        intended(hasComponent(BalanceActivity.class.getName()));

        ArrayList<Float> iData = new ArrayList<>();
        ArrayList<Float> eData = new ArrayList<>();
        float balance = 0;
        float expenseSum = 0;
        float incomeSum = 0;
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Incomes")) {
                iData.add((float) entry.getAmount());
            } else if (entry.getType().equals("Expenses")) {
                eData.add((float) entry.getAmount());
            }
        }
        for (float index : iData) {
            balance += index;
            incomeSum += index;
        }
        for (float index : eData) {
            balance -= index;
            expenseSum += index;
        }
        onView(withId(R.id.textView))
                .check(matches(withText(String.valueOf(balance))));
        Intents.release();
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}