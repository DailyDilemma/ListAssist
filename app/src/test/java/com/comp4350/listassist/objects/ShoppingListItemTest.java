package com.comp4350.listassist.objects;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShoppingListItemTest {

    @Test
    public void testListItem() {
        ShoppingListItem testItem;

        System.out.println("starting testShoppingListItem");

        testItem = new ShoppingListItem();

        assertNotNull(testItem);

        System.out.println("finished testShoppingListItem");
    }
}