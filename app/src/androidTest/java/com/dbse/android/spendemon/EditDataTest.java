package com.dbse.android.spendemon;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}