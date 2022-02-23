package com.example.ca1;

import static org.junit.Assert.*;

public class myLinkedListTest {
    public MyLinkedList<String> Test = new MyLinkedList<>();


    @org.junit.Before
    public void setUp() throws Exception {
        for (int i = 0; i < 10; i++) {
            String test = "Test" + i;
            Test.addElement(test);
        }

    }


    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addElement() {
        assertEquals("Test9", Test.get(0));
        assertEquals("Test8", Test.get(1));
        assertEquals("Test6", Test.get(3));
        assertEquals("Test1", Test.get(8));
        assertNotEquals("Test3", Test.get(7));
        Test.addElement("Deez");
        assertEquals("Deez", Test.get(0));


    }

    @org.junit.Test
    public void clearList() {
        Test.clearList();
        assertTrue(Test.size() == 0);
    }

    @org.junit.Test
    public void size() {
        assertEquals(10, Test.size());
        System.out.println(Test.size());
    }



    @org.junit.Test
    public void isEmpty() {
        assertEquals(10, Test.size());
        Test.clearList();
        assertEquals(0, Test.size());

    }

    @org.junit.Test
    public void get() {
        assertEquals("Test9", Test.get(0));
        assertEquals("Test7", Test.get(2));
        assertEquals("Test5", Test.get(4));
        assertNotEquals("Test0", Test.get(8));


    }

    @org.junit.Test
    public void deleteElement() {

        //testing deleting the head of the list
        assertEquals("Test9", Test.get(0));
        Test.deleteElement(0);
        assertNotEquals("Test9", Test.get(0));


    }
}