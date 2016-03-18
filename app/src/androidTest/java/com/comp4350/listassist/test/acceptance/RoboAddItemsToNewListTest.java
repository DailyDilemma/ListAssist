package com.comp4350.listassist.test.acceptance;

import com.comp4350.listassist.presentation.ViewActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class RoboAddItemsToNewListTest extends ActivityInstrumentationTestCase2<ViewActivity> {
  	private Solo solo;
  	
  	public RoboAddItemsToNewListTest() {
		super(ViewActivity.class);
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
		solo.waitForActivity(com.comp4350.listassist.presentation.MainActivity.class, 2000);

        //Press menu back key
		solo.goBack();
	}
}
