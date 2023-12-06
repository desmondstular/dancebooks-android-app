package com.example.javaapp.database_v2;

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
        Assertions.assertEquals(classModel1.getClassName(), classModel1.getClassName());
        Assertions.assertNotEquals(classModel2.getClassName(), classModel3.getClassName());
    }

    @Test
    void getYear() {
        Assertions.assertEquals(classModel1.getYear(), classModel2.getYear());
        Assertions.assertNotEquals(classModel3.getYear(), classModel1.getYear());
        Assertions.assertEquals(2020, classModel1.getYear());
    }

    @Test
    void getCost() {
        Assertions.assertEquals(500F, classModel1.getCost());
        Assertions.assertEquals(600F, classModel3.getCost());
        Assertions.assertNotEquals(classModel2.getCost(), classModel3.getCost(), 0.0);
    }

    @Test
    void getCapacity() {
        Assertions.assertEquals(classModel1.getCapacity(), classModel2.getCapacity());
        Assertions.assertEquals(40, classModel3.getCapacity());
        Assertions.assertNotEquals(classModel2.getCapacity(), classModel3.getCapacity());
    }
    @Test
    void getEnrolled() {
        Assertions.assertEquals(classModel1.getEnrolled(), classModel2.getEnrolled());
        Assertions.assertNotEquals(classModel3.getEnrolled(), classModel2.getEnrolled());
        Assertions.assertEquals(0, classModel3.getEnrolled());
        }

//return  className + " " + year + " Cost: " + cost + " Capacity: " + enrolled + "/" + capacity;
    @Test
    void testToString() {
        Assertions.assertEquals("Tango 2020 Cost: 500.0 Capacity: 20/25", classModel1.toString() );
        Assertions.assertEquals(classModel2.toString(), classModel1.toString());
        Assertions.assertEquals("Salsa 2023 Cost: 600.0 Capacity: 0/40",  classModel3.toString());
    }
}