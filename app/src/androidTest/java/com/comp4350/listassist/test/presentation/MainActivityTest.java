package com.comp4350.listassist.test.presentation;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.comp4350.listassist.presentation.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @UiThreadTest
    public void testActivityExists() {
        MainActivity actv = getActivity();
        assertNotNull(actv);
    }
}
