package com.example.javaapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.javaapp.database_v2.ClientModel;
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
public class DatabaseDaoTestClient {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    DatabaseDao databaseDao = new DatabaseDao(appContext);
    ClientModel clientModel1 = new ClientModel("ANDY@GMAIL.COM",
            "ANDY", "BARTKO", "5877842163", 0F);
    ClientModel clientModel2 = new ClientModel("GABE@GMAIL.COM", "GABE", "JORAND",
            "7807081234", 1000F);
    ClientModel clientModel3 = new ClientModel("DESMOND@GMAIL.COM", "DESMOND", "STULAR",
            "7805802301", 10000F);
    @Before
    public void addClients(){
        databaseDao.addOneClient(clientModel1);
        databaseDao.addOneClient(clientModel2);
        databaseDao.addOneClient(clientModel3);
    }

    @Test
    public void addOneClient() {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //assertEquals("com.example.javaapp", appContext.getPackageName());
        assertFalse(databaseDao.addOneClient(clientModel1));
        //assertFalse(databaseDao.addOneClient(clientModel1));
        assertFalse(databaseDao.addOneClient(clientModel2));
        assertFalse(databaseDao.addOneClient(clientModel3));
    }
    @Test
    public void getOneClientByPrimaryKey() {
        ClientModel clientModelActual = databaseDao.getOneClientByPrimaryKey("ANDY@GMAIL.COM");
        assertEquals(clientModel1, clientModelActual);
    }
    @Test
    public void getAllClients(){
        List<ClientModel> clientModels = new ArrayList<>();
        clientModels.add(clientModel1);
        clientModels.add(clientModel2);
        clientModels.add(clientModel3);
        assertEquals(clientModels, databaseDao.getAllClients());
    }

    @Test
    public void getAllClientWithBalanceGreaterThanZero(){
        List<ClientModel> clientModels = new ArrayList<>();
        clientModels.add(clientModel2);
        clientModels.add(clientModel3);
        assertEquals(clientModels, databaseDao.getAllClientWithBalanceGreaterThanZero());
    }

}