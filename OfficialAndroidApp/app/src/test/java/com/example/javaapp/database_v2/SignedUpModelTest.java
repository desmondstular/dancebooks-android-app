package com.example.javaapp.database_v2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SignedUpModelTest{
    SignedUpModel signedUpModel1 = new SignedUpModel("Andy@gmail.com", "Salsa",
            2020, 1, 1);
    SignedUpModel signedUpModel2 = new SignedUpModel("Des@gmail.com", "Tango",
            2021, 0, 2);
    SignedUpModel signedUpModel3 = new SignedUpModel("Gabe@gmail.com", "Ballet",
            2019, 0, 3);

    @Test
    void getEmail() {
        assertEquals("Andy@gmail.com", signedUpModel1.getEmail());
        assertEquals("Des@gmail.com", signedUpModel2.getEmail());
        assertEquals("Gabe@gmail.com", signedUpModel3.getEmail());
    }

    @Test
    void getClassName() {
        assertEquals("Salsa", signedUpModel1.getClassName());
        assertEquals("Tango", signedUpModel2.getClassName());
        assertEquals("Ballet", signedUpModel3.getClassName());
    }

    @Test
    void getYear() {
        assertEquals(2020, signedUpModel1.getYear());
        assertEquals(2021, signedUpModel2.getYear());
        assertEquals(2019, signedUpModel3.getYear());
    }

    @Test
    void getIsPaid() {
        assertEquals(1, signedUpModel1.getIsPaid());
        assertEquals(0, signedUpModel2.getIsPaid());
        assertEquals(0, signedUpModel3.getIsPaid());
    }

    @Test
    void getInvoiceID() {
        assertEquals(1, signedUpModel1.getInvoiceID());
        assertEquals(2, signedUpModel2.getInvoiceID());
        assertEquals(3, signedUpModel3.getInvoiceID());
    }

    @Test
    void testToString() {
        //email + " " + className + " " + year + " invID: " +invoiceID;
        assertEquals("Andy@gmail.com Salsa 2020 invID: 1", signedUpModel1.toString());
        assertEquals("Des@gmail.com Tango 2021 invID: 2",signedUpModel2.toString());
        assertEquals("Gabe@gmail.com Ballet 2019 invID: 3",signedUpModel3.toString());
    }

    @Test
    void testEquals() {
        SignedUpModel signedUpModel = new SignedUpModel("Andy@gmail.com","Salsa",
                                                2020, 1, 1 );
        assertTrue(signedUpModel.equals(signedUpModel1));
        assertFalse(signedUpModel1.equals(signedUpModel2));
    }
}