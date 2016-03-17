package com.comp4350.listassist.test;

import com.comp4350.listassist.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class MainActivityFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public MainActivityFragmentTest() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.comp4350.listassist.MainActivity'
		solo.waitForActivity(com.comp4350.listassist.MainActivity.class, 2000);
        //Set default small timeout to 13947 milliseconds
		Timeout.setSmallTimeout(13947);
        //Click on ImageView
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.new_list_button));
        //Enter the text: 'New List Name'
		solo.clearEditText((android.widget.EditText) solo.getView(com.comp4350.listassist.R.id.editText));
		solo.enterText((android.widget.EditText) solo.getView(com.comp4350.listassist.R.id.editText), "New List Name");
        assertTrue(solo.searchEditText("New List Name"));
        //Click on Confirm
		solo.clickOnView(solo.getView(android.R.id.button1));
	}
}
