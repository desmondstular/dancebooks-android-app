package com.example.javaapp.database_v2;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ClassModelTest {
    static ClassModel classModel1;
    static ClassModel classModel2;
    static ClassModel classModel3;
    @BeforeAll
    static void beforeAll() {
        classModel1 = new ClassModel("Tango", 2020, 500F, 25, 20);
        classModel2 = new ClassModel("Tango", 2020, 500F, 25, 20);
        classModel3 = new ClassModel("Salsa", 2023, 600F, 40, 0);
    }

    @Test
    void getClassName() {
        assertTrue(classModel1.getClassName().equals(classModel1.getClassName()));
        assertFalse(classModel2.getClassName().equals(classModel3.getClassName()));
    }

    @Test
    void getYear() {
        assertEquals(classModel1.getYear(), classModel2.getYear());
        assertNotEquals(classModel3.getYear(),classModel1.getYear());
        assertTrue(classModel1.getYear() == 2020);
    }

    @Test
    void getCost() {
        assertTrue(classModel1.getCost() == 500F);
        assertTrue(classModel3.getCost() == 600F);
        assertFalse(classModel2.getCost() == classModel3.getCost());
    }

    @Test
    void getCapacity() {
        assertTrue(classModel1.getCapacity() == classModel2.getCapacity());
        assertTrue(classModel3.getCapacity() == 40);
        assertFalse(classModel2.getCapacity() == classModel3.getCapacity());
    }
    @Test
    void getEnrolled() {
            assertTrue(classModel1.getEnrolled() == classModel2.getEnrolled());
            assertFalse(classModel3.getEnrolled() == classModel2.getEnrolled());
            assertTrue(classModel3.getEnrolled() == 0);
        }

//return  className + " " + year + " Cost: " + cost + " Capacity: " + enrolled + "/" + capacity;
    @Test
    void testToString() {
        assertEquals("Tango 2020 Cost: 500.0 Capacity: 20/25", classModel1.toString() );
        assertEquals(classModel2.toString(), classModel1.toString());
        assertEquals("Salsa 2023 Cost: 600.0 Capacity: 0/40",  classModel3.toString());
    }
}