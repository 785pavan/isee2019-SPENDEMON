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
    

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}