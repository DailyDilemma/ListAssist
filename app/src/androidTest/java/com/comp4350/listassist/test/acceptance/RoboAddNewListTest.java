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
        //Wait for activity: 'com.comp4350.listassist.MainActivity'
		assertTrue(solo.waitForActivity(com.comp4350.listassist.presentation.MainActivity.class, 2000));


        //Click on Confirm
		solo.clickOnView(solo.getView(android.R.id.button1));
	}
}
