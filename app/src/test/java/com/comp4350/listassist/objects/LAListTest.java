package com.comp4350.listassist.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class LAListTest {

    @Test
    public void testLAList() {
        LAList testList;

        System.out.println("starting testLAList");

        testList = new LAList();
        testList.setName("TestList1");
        testList.setId(1);

        assertNotNull("list does not exist", testList);
        assertTrue("TestList1".equals(testList.getName()));
        assertEquals(1, (int) testList.getId());
        assertNotNull("no item list found", testList.getShoppingListItems());
        assertEquals(testList.getShoppingListItems().size(), 0);
        assertNotNull("no additional properties found", testList.getAdditionalProperties());
        assertEquals(testList.getAdditionalProperties().size(), 0);

        System.out.println("finished testLAList");
    }
}
