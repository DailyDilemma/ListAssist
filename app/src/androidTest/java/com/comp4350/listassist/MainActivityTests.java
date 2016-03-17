package com.comp4350.listassist;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity actv = getActivity();
        assertNotNull(actv);
    }
}
