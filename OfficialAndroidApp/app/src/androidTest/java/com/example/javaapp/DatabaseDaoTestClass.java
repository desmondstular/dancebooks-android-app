package com.example.javaapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseDaoTestClass {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    DatabaseDao databaseDao = new DatabaseDao(appContext);
    ClassModel classModel1 = new ClassModel("Tango", 2020, 500F, 20, 10);
    ClassModel classModel2 = new ClassModel("Salsa", 2023, 300F, 20, 20);
    ClassModel classModel3 = new ClassModel("Street", 2019, 150F, 10, 1);

    @Before
    public void addClasses(){
        databaseDao.addOneClass(classModel1);
        databaseDao.addOneClass(classModel2);
        databaseDao.addOneClass(classModel3);
    }
    @Test
    public void addOneClass(){
        assertFalse(databaseDao.addOneClass(classModel1));
        assertFalse(databaseDao.addOneClass(classModel2));
        assertFalse(databaseDao.addOneClass(classModel3));
    }
    @Test
    public void getOneClassByPrimaryKey(){
        assertEquals(classModel1, databaseDao.getOneClassByPrimaryKey("Tango", 2020));
        assertEquals(classModel2, databaseDao.getOneClassByPrimaryKey("Salsa", 2023));
        assertEquals(classModel3, databaseDao.getOneClassByPrimaryKey("Street", 2019));
    }
    @Test
    public void getAllClasses(){
        List<ClassModel> classModels = new ArrayList<>();
        classModels.add(classModel1);
        classModels.add(classModel2);
        classModels.add(classModel3);
        assertEquals(classModels, databaseDao.getAllClasses());
    }

    @Test
    public void getAllAvailableClasses(){
        List<ClassModel> classModels = new ArrayList<>();
        classModels.add(classModel1);
        classModels.add(classModel3);
        assertEquals(classModels, databaseDao.getAllAvailableClasses());
    }

}