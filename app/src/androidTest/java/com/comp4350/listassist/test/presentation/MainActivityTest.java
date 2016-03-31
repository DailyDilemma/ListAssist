package com.comp4350.listassist.test.presentation;

import android.app.Instrumentation.*;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.comp4350.listassist.presentation.AddingDialog;
import com.comp4350.listassist.presentation.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @UiThreadTest
    public void testActivityExists() {
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);
    }

    public void testAddListButton() {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddingDialog.class.getName(), null, false);

        MainActivity mainActivity = getActivity();
    }
}
