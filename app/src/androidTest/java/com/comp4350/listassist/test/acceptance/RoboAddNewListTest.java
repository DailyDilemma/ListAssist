package com.comp4350.listassist.test.acceptance;

import com.comp4350.listassist.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class RoboAddNewListTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public RoboAddNewListTest() {
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

		//Click on Add button
        solo.clickOnImageButton(1);

        solo.clickOnEditText(0);
        solo.enterText(0, "New Robo List");

        solo.clickOnButton(1);
        
        solo.goBack();
	}
}
