package com.comp4350.listassist;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class ViewActivityTests extends ActivityInstrumentationTestCase2<ViewActivity> {

    public ViewActivityTests() {
        super(ViewActivity.class);
    }

    public void testActivityExists() {
        ViewActivity actv = getActivity();
        assertNotNull(actv);
    }
}
