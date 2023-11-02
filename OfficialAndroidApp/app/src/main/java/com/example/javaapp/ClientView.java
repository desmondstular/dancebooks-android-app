package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.javaapp.database.DatabaseHelper;
import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;

import java.util.List;

public class ClientView extends AppCompatActivity {
    Button addClientsBtn, addClassBtn, addInvoiceBtn,viewInvoiceBtn, viewClassBtn;
    ListView lv_clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        lv_clientList = findViewById(R.id.lv_clientList);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start**********************************************************************
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, AddClient.class));
        });
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, MainActivity.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, ClassSignUp.class));
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, SignUpView.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, DanceClassView.class));
        });
        //Navigation Bar End ***********************************************************************
        //View the Database
//        DatabaseHelper databaseHelperClient = new DatabaseHelper(ClientView.this);
//        List<ClientModel> everyClient = databaseHelperClient.getAllClients();
//        ArrayAdapter clientArrayAdapter = new ArrayAdapter<ClientModel>(ClientView.this,
//                android.R.layout.simple_list_item_1, everyClient);
//        lv_clientList.setAdapter(clientArrayAdapter);
        DatabaseDao databaseDao = new DatabaseDao(ClientView.this);
        List<ClientModel> everyClient = databaseDao.getAllClients();
        ArrayAdapter clientArrayAdapter = new ArrayAdapter<ClientModel>(ClientView.this,
                android.R.layout.simple_list_item_1, everyClient);
        lv_clientList.setAdapter(clientArrayAdapter);





        //------------------------------------------------------------------------------------------
    }
}
