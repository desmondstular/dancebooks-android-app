package com.example.javaapp.database_v2;

import static org.junit.jupiter.api.Assertions.*;

import android.database.sqlite.SQLiteOpenHelper;

import org.junit.jupiter.api.Test;

class InvoiceModelTest {
    InvoiceModel invoiceModel1 = new InvoiceModel(1, "ANDY@GMAIL.COM", 500F,
            1);
    InvoiceModel invoiceModel2 = new InvoiceModel(2, "DESMOND@GMAIL.COM", 200F,
            0);
    InvoiceModel invoiceModel3 = new InvoiceModel(3, "GABE@GMAIL.COM", 500F, 0);

    @Test
    void getInvoiceID() {
        assertEquals(1, invoiceModel1.getInvoiceID());
        assertEquals(2, invoiceModel2.getInvoiceID());
        assertEquals(3, invoiceModel3.getInvoiceID());
    }

    @Test
    void getEmail() {
        assertEquals("ANDY@GMAIL.COM", invoiceModel1.getEmail());
        assertEquals("DESMOND@GMAIL.COM", invoiceModel2.getEmail());
        assertEquals("GABE@GMAIL.COM", invoiceModel3.getEmail());
    }

    @Test
    void getTotalCost() {
        assertEquals(500F, invoiceModel1.getTotalCost());
        assertEquals(200F, invoiceModel2.getTotalCost());
        assertEquals(500F, invoiceModel3.getTotalCost());
    }

    @Test
    void getIsPaid() {
        assertEquals(1, invoiceModel1.getIsPaid());
        assertEquals(0, invoiceModel2.getIsPaid());
        assertEquals(0, invoiceModel3.getIsPaid());
    }

    @Test
    void testToString() {
        assertEquals("invID: 1\n ANDY@GMAIL.COM \nTotal: 500.0 \n1",
                invoiceModel1.toString());
        assertEquals("invID: 2\n DESMOND@GMAIL.COM \nTotal: 200.0 \n0",
                invoiceModel2.toString());
        assertEquals("invID: 3\n GABE@GMAIL.COM \nTotal: 500.0 \n0",
                invoiceModel3.toString());
    }

    @Test
    void testEquals() {
        InvoiceModel invoiceModel4 = new InvoiceModel(1, "ANDY@GMAIL.COM", 500F,
                1);
        assertTrue(invoiceModel1.equals(invoiceModel4));
    }
}