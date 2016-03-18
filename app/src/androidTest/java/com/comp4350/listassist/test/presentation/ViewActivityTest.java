package com.comp4350.listassist.test.presentation;

import android.test.ActivityInstrumentationTestCase2;
import com.comp4350.listassist.presentation.ViewActivity;

public class ViewActivityTest extends ActivityInstrumentationTestCase2<ViewActivity> {

    public ViewActivityTest() {
        super(ViewActivity.class);
    }

    public void testActivityExists() {
        ViewActivity actv = getActivity();
        assertNotNull(actv);
    }
}
