package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.javaapp.database.ClientModel;
import com.example.javaapp.database.DatabaseHelper;

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
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(ClientView.this, AddClient.class);
            startActivity(intent);
        });
        // Set click listener for View Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(ClientView.this, MainActivity.class);
            startActivity(intent);
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(ClientView.this, AddInvoices.class);
            startActivity(intent);
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ClientView.this, InvoiceView.class);
            startActivity(intent);
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(ClientView.this, DanceClassView.class);
            startActivity(intent);
        });
        DatabaseHelper databaseHelperClient = new DatabaseHelper(ClientView.this);
        List<ClientModel> everyClient = databaseHelperClient.getAllClients();
        ArrayAdapter clientArrayAdapter = new ArrayAdapter<ClientModel>(ClientView.this,
                android.R.layout.simple_list_item_1, everyClient);
        lv_clientList.setAdapter(clientArrayAdapter);





        //------------------------------------------------------------------------------------------
    }
}
