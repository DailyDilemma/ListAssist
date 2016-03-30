package com.comp4350.listassist.objects;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LAListTest {

    @Test
    public void testLAList() {
        LAList testList;
        ShoppingListItem testItem;

        System.out.println("starting testLAList");

        testList = new LAList();
        testList.setName("TestList1");
        testList.setId(1);

        testItem = new ShoppingListItem();
        testItem.setDescription("TestItem1");

        assertNotNull("list does not exist", testList);
        assertTrue("TestList1".equals(testList.getName()));
        assertEquals(1, (int) testList.getId());
        assertNotNull("no item list found", testList.getShoppingListItems());
        assertNotNull("no additional properties found", testList.getAdditionalProperties());

        System.out.println("finished testLAList");
    }
}
