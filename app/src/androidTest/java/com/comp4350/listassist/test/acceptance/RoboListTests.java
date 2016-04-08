package com.comp4350.listassist.test.acceptance;

import com.comp4350.listassist.R;
import com.comp4350.listassist.presentation.MainActivity;
import com.comp4350.listassist.presentation.ViewActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

public class RoboListTests extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
    private int indexOfView = 2; //This hack is the position in the table that the Robo test list is created
  	
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
  
	public void test01_RoboCreateEmptyList() {
		//Wait for Main activity
		assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

		//Click on Add button
        solo.clickOnImageButton(1);
        assertTrue("did not get adding dialog", solo.searchText("New List"));

		//Click on Text field
        solo.clickOnEditText(0);
        solo.enterText(0, "Robo Test List");

		//Click on Confirm button
        solo.clickOnButton(1);
        assertTrue("new list not added", solo.searchText("Robo Test List"));
        
        solo.goBack();
	}

    public void test02_RoboAddItemToNewList() {
        //Wait for Main activity
        assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

        //Click on Open button for Robo Test List
        assertNotNull(solo.getCurrentViews());
        solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button, indexOfView));
        assertTrue("did not open view activity", solo.waitForActivity(ViewActivity.class));

        //Click on Add Item button
        solo.clickOnImageButton(1);
        assertTrue("did not get adding dialog", solo.searchText("New Item"));

        //Click on Text Field
        solo.clickOnEditText(0);
        solo.enterText(0, "Test Item 1");

        //Click on Confirm button
        solo.clickOnButton(1);
        assertTrue("new item not added", solo.searchText("Test Item 1"));

        solo.goBack();
    }

    public void test03_RoboCheckItemFromList() {
        //Wait for Main activity
        assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

        //Click on Open button for Robo Test List
        assertNotNull(solo.getCurrentViews());
        solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button, indexOfView));
        assertTrue("did not open view activity", solo.waitForActivity(ViewActivity.class));

        //Click on Checkbox for Test Item 1
        solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.item, 0));
        assertTrue("test item not checked", solo.isCheckBoxChecked(0));

        solo.goBack();
    }

    public void test04_RoboDeleteItemFromList() {
        //Wait for Main activity
        assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

        //Click on Open button for Robo Test List
        assertNotNull(solo.getCurrentViews());
        solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.open_button, indexOfView));
        assertTrue("did not open view activity", solo.waitForActivity(ViewActivity.class));

        //Click on Delete button for Test Item 1
        solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.delete_button, 0));
        assertFalse("test item not deleted", solo.searchText("Test Item 1"));

        solo.goBack();
    }

    public void test05_RoboDeleteEmptyList() {
        //Wait for Main activity
        assertTrue("did not get main activity", solo.waitForActivity("MainActivity"));

        //Click on Delete button for Robo Test List
        assertNotNull(solo.getCurrentViews());
        solo.clickOnView(solo.getView(com.comp4350.listassist.R.id.delete_button, indexOfView));
        assertFalse("test list not deleted", solo.searchText("Robo Test List"));

        solo.goBack();
    }
}
