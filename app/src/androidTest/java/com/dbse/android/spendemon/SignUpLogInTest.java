package com.dbse.android.spendemon;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class SignUpLogInTest extends IntentsTestRule {

    @Rule
    public ActivityTestRule<SignUpLogIn> mActivityTestRule = new ActivityTestRule<>(SignUpLogIn.class);

    private String username = "admin";
    private int password = 12345;
    private SignUpLogIn mActivity = null;
    private SharedPreferences sharedPreferences;
    private Instrumentation instrumentation;

    public SignUpLogInTest() {
        super(SignUpLogIn.class);
    }

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
        sharedPreferences = mActivity.getSharedPreferences("SignUp", 0);
        instrumentation = InstrumentationRegistry.getInstrumentation();


    }

    @Test
    public void testLaunchViews() {
        Intents.init();
        View editText = mActivity.findViewById(R.id.username1);
        assertNotNull(editText);
        editText = mActivity.findViewById(R.id.password1);
        assertNotNull(editText);
        View button = mActivity.findViewById(R.id.buttonSignUp);
        assertNotNull(button);

        mActivityTestRule.launchActivity(new Intent());
        if (mActivity.signUpModeActive) {
            Espresso.onView(withId(R.id.changeSignUpMode))
                    .perform(ViewActions.click());
            Espresso.onView((withId(R.id.username1)))
                    .perform(ViewActions.typeText(username));
            Espresso.onView(withId(R.id.password1))
                    .perform(ViewActions.typeText(String.valueOf(password)));
            Espresso.pressBack();
            Espresso.onView(withId(R.id.buttonSignUp))
                    .perform(ViewActions.click());
            assertEquals(sharedPreferences.getString("UserName", null), username);
            assertEquals(sharedPreferences.getInt("Password", 0), password);
        }

        Espresso.onView((withId(R.id.username1)))
                .perform(ViewActions.typeText(username));
        Espresso.onView(withId(R.id.password1))
                .perform(ViewActions.typeText(String.valueOf(password)));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.buttonSignUp))
                .perform(ViewActions.click());
        assertEquals(sharedPreferences.getString("UserName", null), username);
        assertEquals(sharedPreferences.getInt("Password", 0), password);
        intended(hasComponent(Summary.class.getName()));
        Intents.release();

    }

    @Test
    public void wrongPassTest() {
        View relativeLayout = mActivity.findViewById(R.id.relativeLayout1);
        Espresso.onView((withId(R.id.username1)))
                .perform(ViewActions.typeText(username));
        int wrongPass = 123456789;
        Espresso.onView(withId(R.id.password1))
                .perform(ViewActions.typeText(String.valueOf(wrongPass)));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.buttonSignUp))
                .perform(ViewActions.click());
        assertEquals(sharedPreferences.getString("UserName", null), username);
        assertNotEquals(sharedPreferences.getInt("Password", 0), wrongPass);
        assertNotNull(relativeLayout);
    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;

    }

}