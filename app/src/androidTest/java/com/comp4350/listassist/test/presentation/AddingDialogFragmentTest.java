package com.comp4350.listassist.test.presentation;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ImageButton;
import android.app.Fragment;
import android.app.FragmentManager;
import android.test.ActivityUnitTestCase;

import com.comp4350.listassist.R;
import com.comp4350.listassist.presentation.MainActivity;

public class AddingDialogFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public AddingDialogFragmentTest() {
        super(MainActivity.class);
    }

    private MainActivity mainActivity;

    @Override
    protected void setUp() {
        super.setUp();
        startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class));
    }

    @UiThreadTest
    public void testAddingDialogFragmentExists() {
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);
    }
}
