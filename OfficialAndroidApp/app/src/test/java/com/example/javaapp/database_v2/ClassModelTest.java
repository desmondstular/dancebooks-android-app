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
    }
//
//    @Test
//    void getCost() {
//    }
//
//    @Test
//    void getCapacity() {
//    }
//
//    @Test
//    void getEnrolled() {
//    }
//
//    @Test
//    void testToString() {
//    }
}