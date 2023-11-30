package com.example.javaapp.database_v2;

import static org.junit.jupiter.api.Assertions.*;

import com.example.javaapp.database_v2.ClientModel;

import org.junit.jupiter.api.Test;

class ClientModelTest {
    ClientModel clientModel1 = new ClientModel("ANDY@GMAIL.COM",
            "ANDY", "BARTKO", "5877842163", 0F);
    ClientModel clientModel2 = new ClientModel("GABE@GMAIL.COM", "GABE", "JORAND",
            "7807081234", 1000F);
    ClientModel clientModel3 = new ClientModel("DESMOND@GMAIL.COM", "DESMOND", "STULAR",
            "7805802301", 10000F);
    ClientModel clientModel4 = new ClientModel("ANDY@GMAIL.COM",
            "Andy", "Bartko", "5877842163", 0F);
    @Test
    void getEmail() {
        assertTrue(clientModel1.getEmail().equals(clientModel4.getEmail()));
        assertNotEquals(clientModel2.getEmail(), clientModel3.getEmail());
    }

    @Test
    void getFirstName() {
        assertEquals("ANDY",clientModel1.getFirstName());
        assertNotEquals(clientModel2.getFirstName(), clientModel3.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Bartko", clientModel4.getLastName());
        assertNotEquals(clientModel2.getLastName(), clientModel3.getLastName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("5877842163", clientModel1.getPhoneNumber());
        assertNotEquals("1213165", clientModel2.getPhoneNumber());
    }

    @Test
    void getBalance() {
        assertNotEquals(1000F, clientModel1.getBalance());
        assertEquals(1000F, clientModel2.getBalance());
        assertTrue(clientModel1.getBalance() == clientModel4.getBalance());
    }
    //firstName + " " + lastName + " " + email + " " +   " " + "Balance: " + balance;
    @Test
    void testToString() {
        assertEquals("ANDY BARTKO ANDY@GMAIL.COM  Balance: 0.0", clientModel1.toString());
        assertNotEquals("ANDY BARTKO ANDY@GMAIL.COM  Balance: 0.0",
                clientModel2.toString());

    }

    @Test
    void getFnameLnameEmail() {
        assertEquals("ANDY, BARTKO, ANDY@GMAIL.COM", clientModel1.getFnameLnameEmail());
        assertEquals("GABE, JORAND, GABE@GMAIL.COM", clientModel2.getFnameLnameEmail());
    }
}