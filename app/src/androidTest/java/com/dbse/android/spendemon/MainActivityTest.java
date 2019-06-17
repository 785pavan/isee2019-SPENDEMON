package com.dbse.android.spendemon;

import android.view.Gravity;
import android.view.View;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest extends IntentsTestRule {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mActivity.findViewById(R.id.drawer_layout);
        assertNotNull(view);
    }

    @Test
    public void navBarTestSummary() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_summary));
        intended(hasComponent(Summary.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestOverView() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_balance));
        intended(hasComponent(BalanceActivity.class.getName()));
        Intents.release();

    }

    @Test
    public void navBarTestMonthly() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_monthly));
        intended(hasComponent(ChartMonthActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestAll() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_total));
        intended(hasComponent(PieChartActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestDaily() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_daily));
        intended(hasComponent(PieChartDailyActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestTrendLine() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_trendLine));
        intended(hasComponent(TrendLineActivity.class.getName()));
        Intents.release();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}