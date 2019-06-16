package com.dbse.android.spendemon;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SignUpLogInTest {

    @Rule
    public ActivityTestRule<SignUpLogIn> mActivityTestRule = new ActivityTestRule<>(SignUpLogIn.class);

    private SignUpLogIn mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunchViews() {
        View editText = mActivity.findViewById(R.id.username1);
        assertNotNull(editText);
        editText = mActivity.findViewById(R.id.password1);
        assertNotNull(editText);
        View button = mActivity.findViewById(R.id.buttonSignUp);
        assertNotNull(button);
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;

    }

    @Test
    public void signUpOrLogIn() {

    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onClick() {
    }

    @Test
    public void onKey() {
    }
}