package com.comp4350.listassist.test.presentation;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ImageButton;

import com.comp4350.listassist.R;
import com.comp4350.listassist.presentation.ViewActivity;

public class ViewActivityTest extends ActivityInstrumentationTestCase2<ViewActivity> {

    public ViewActivityTest() {
        super(ViewActivity.class);
    }

    @UiThreadTest
    public void testViewActivityExists() {
        ViewActivity viewActivity = getActivity();
        assertNotNull(viewActivity);
    }

    public void testRefreshViewButtonExists() {
        ViewActivity viewActivity = getActivity();
        final ImageButton button = (ImageButton) viewActivity.findViewById(R.id.refresh_list);
        assertNotNull(button);
    }

    public void testAddListButtonExists() {
        ViewActivity viewActivity = getActivity();
        final ImageButton button = (ImageButton) viewActivity.findViewById(R.id.add_item);
        assertNotNull(button);
    }
}
