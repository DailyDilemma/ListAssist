package com.comp4350.listassist.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingListItemTest {

    @Test
    public void testShoppingListItem() {
        ShoppingListItem testItem;

        System.out.println("starting testShoppingListItem");

        testItem = new ShoppingListItem();
        testItem.setDescription("TestItem1");
        testItem.setId(1);
        testItem.setChecked(true);
        testItem.setListId(1);

        assertNotNull("shopping list item does not exist", testItem);
        assertTrue("TestItem1".equals(testItem.getDescription()));
        assertEquals(1, (int) testItem.getId());
        assertEquals(true, testItem.getChecked());
        assertEquals(1, (int) testItem.getListId());

        System.out.println("finished testShoppingListItem");
    }
}