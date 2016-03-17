package com.comp4350.listassist.test;

import com.comp4350.listassist.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class ViewActivityFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public ViewActivityFragmentTest() {
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
        //Click on Open
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button));
        //Wait for activity: 'com.comp4350.listassist.ViewActivity'
		assertTrue("com.comp4350.listassist.ViewActivity is not found!", solo.waitForActivity(com.comp4350.listassist.ViewActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.add_item));
        //Enter the text: 'New Item Name'
		solo.clearEditText((android.widget.EditText) solo.getView(com.comp4350.listassist.R.id.editText));
		solo.enterText((android.widget.EditText) solo.getView(com.comp4350.listassist.R.id.editText), "New Item Name");
        assertTrue(solo.searchEditText("New Item Name"));
        //Click on Confirm
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Press menu back key
		solo.goBack();
	}
}
