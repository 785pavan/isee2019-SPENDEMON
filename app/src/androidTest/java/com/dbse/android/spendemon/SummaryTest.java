package com.dbse.android.spendemon;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SummaryTest {

    @Rule
    public ActivityTestRule<Summary> mActivityTestRule = new ActivityTestRule<>(Summary.class);

    private Summary mSummary = null;

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

    @After
    public void tearDown() throws Exception {
        mSummary = null;
    }
}