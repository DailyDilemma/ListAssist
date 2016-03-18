package com.comp4350.listassist.test;

import com.comp4350.listassist.presentation.MainActivity;
import com.comp4350.listassist.presentation.ViewActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class RoboClickButtons extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public RoboClickButtons() {
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
        //Wait for activity: 'com.comp4350.listassist.presentation.MainActivity'
		solo.waitForActivity(MainActivity.class, 2000);
        //Click on Delete
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.delete_button));
        //Click on Delete
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.delete_button, 1));
        //Click on Delete
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.delete_button, 2));
        //Click on Open
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button, 2));
        //Wait for activity: 'com.comp4350.listassist.presentation.ViewActivity'
		assertTrue("com.comp4350.listassist.presentation.ViewActivity is not found!", solo.waitForActivity(ViewActivity.class));
        //Click on Test item 0
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item));
        //Click on Test item 1
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 1));
        //Click on Test item 2
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 2));
        //Click on Test item 2
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 2));
        //Click on Test item 1
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 1));
        //Click on Test item 0
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item));
        //Press menu back key
		solo.goBack();
        //Click on Open
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button, 1));
        //Wait for activity: 'com.comp4350.listassist.presentation.ViewActivity'
		assertTrue("com.comp4350.listassist.presentation.ViewActivity is not found!", solo.waitForActivity(ViewActivity.class));
        //Click on Test item 0
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item));
        //Click on Test item 1
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 1));
        //Click on Test item 2
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 2));
        //Click on Test item 2
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 2));
        //Click on Test item 1
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 1));
        //Click on Test item 0
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item));
        //Press menu back key
		solo.goBack();
        //Click on Open
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button));
        //Wait for activity: 'com.comp4350.listassist.presentation.ViewActivity'
		assertTrue("com.comp4350.listassist.presentation.ViewActivity is not found!", solo.waitForActivity(ViewActivity.class));
        //Click on Test item 0
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item));
        //Click on Test item 1
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 1));
        //Click on Test item 2
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 2));
        //Click on Test item 2
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 2));
        //Click on Test item 1
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 1));
        //Click on Test item 0
		solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item));
        //Press menu back key
		solo.goBack();
	}
}
