package com.comp4350.listassist.test.acceptance;

import com.comp4350.listassist.R;
import com.comp4350.listassist.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

public class RoboListTests extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public RoboListTests() {
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
  
	public void test01_createEmptyList() {
		//Wait for Main activity
		assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

		//Click on Add button
        solo.clickOnImageButton(1);
        assertNotNull("did not get adding dialog", solo.getText("New List"));

		//Click on Text field
        solo.clickOnEditText(0);
        solo.enterText(0, "New Robo List");

		//Click on Confirm button
        solo.clickOnButton(1);
        assertNotNull("new list not added", solo.getText("New Robo List"));
        
        solo.goBack();
	}

    public void test02_addItemToNewList() throws InterruptedException {
        //Wait for Main activity
        assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

        //Click on Open button for New Robo List
//        assertNotNull(solo.getCurrentViews());
//        solo.clickInList(3);
//        solo.wait(10000);

        solo.goBack();
    }
}
