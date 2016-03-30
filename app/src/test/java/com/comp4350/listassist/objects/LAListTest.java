package com.comp4350.listassist.objects;

import org.junit.Test;
import static org.junit.Assert.*;

public class LAListTest {

    @Test
    public void testEmptyList() {
        LAList testList;
        System.out.println("Starting testEmptyList");

        testList = new LAList();
        testList.setName("TestList1");
        testList.setId(1);
        assertNotNull(testList);
        assertTrue("TestList1".equals(testList.getName()));
        assertEquals(1, (int) testList.getId());

        System.out.println("Finished testEmptyList");
    }
}
