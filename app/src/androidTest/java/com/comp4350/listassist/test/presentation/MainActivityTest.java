package com.comp4350.listassist.test.presentation;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ImageButton;

import com.comp4350.listassist.R;
import com.comp4350.listassist.presentation.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @UiThreadTest
    public void testMainActivityExists() {
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);
    }

    public void testRefreshViewButtonIsPresent() {
        MainActivity mainActivity = getActivity();
        final ImageButton button = (ImageButton) mainActivity.findViewById(R.id.refresh_view);
        assertNotNull(button);
    }

    public void testAddListButtonIsPresent() {
        MainActivity mainActivity = getActivity();
        final ImageButton button = (ImageButton) mainActivity.findViewById(R.id.new_list_button);
        assertNotNull(button);
    }
}
