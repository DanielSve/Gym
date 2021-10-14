package com.company;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerHandlerTest {

    CustomerHandler customerHandler = new CustomerHandler();
    ArrayList<Customer> testList;

    @Test
    void convertTextToList() {
        testList = customerHandler.convertTextToList("Test/TestCustomers.txt");

        assertEquals(14, testList.size());
        assertEquals("Olivia Persson", testList.get(3).getName());
        assertEquals("4604151234", testList.get(10).getPersNr());
        assertEquals("2018-12-22", testList.get(12).getMembershipDate());
        assertNotEquals("Olivia Persson", testList.get(10).getName());
    }

    @Test
    void isOnList() {
        testList = customerHandler.convertTextToList("Test/TestCustomers.txt");
        assertTrue(customerHandler.isOnList("Olivia Persson", testList));

        assertFalse(customerHandler.isOnList("Kalle Bengtsson", testList));
        assertTrue(customerHandler.isOnList("5711121234", testList));
        assertFalse(customerHandler.isOnList("2021-01-30", testList));
    }

    @Test
    void checkMembershipStatus() {
        testList = customerHandler.convertTextToList("Test/TestCustomers.txt");

        assertSame("Giltigt medlemskap! Ditt pass registreras",
                customerHandler.checkMembershipStatus("Olivia Persson", testList));
        assertSame("Ditt medlemskap har gått ut!",
                customerHandler.checkMembershipStatus("8906138493", testList));
        assertSame("Du är inte medlem, vänligen lämna gymmet.",
                customerHandler.checkMembershipStatus("PelleJöns", testList));
    }

    @Test
    void userInput() {
    }

}