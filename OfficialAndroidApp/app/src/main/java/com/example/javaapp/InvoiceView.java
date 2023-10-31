package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.javaapp.database_v1.DatabaseHelper;
import com.example.javaapp.database_v1.InvoiceModel;

import java.util.List;

public class InvoiceView extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, addInvoiceBtn, addClassBtn, viewClassBtn;

    ListView lv_Invoice_List;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_view);
        lv_Invoice_List = findViewById(R.id.lv_Invoice_list);
        //Navigation Bar Start *********************************************************************
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(InvoiceView.this, MainActivity.class);
            startActivity(intent);
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(InvoiceView.this, AddInvoices.class);
            startActivity(intent);
        });
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(InvoiceView.this, AddClient.class);
            startActivity(intent);
            //finish(); // Optional: Close this activity if you don't want to keep it in the back stack
        });
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(InvoiceView.this, ClientView.class);
            startActivity(intent);
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(InvoiceView.this, DanceClassView.class);
            startActivity(intent);
        });
        //Navigation Bar End ***********************************************************************


        DatabaseHelper databaseHelper = new DatabaseHelper(InvoiceView.this);
        List<InvoiceModel> everyInvoice = databaseHelper.getAllInvoices();
        ArrayAdapter invoiceArrayAdapter = new ArrayAdapter<InvoiceModel>(InvoiceView.this,
                android.R.layout.simple_list_item_1, everyInvoice);
        lv_Invoice_List.setAdapter(invoiceArrayAdapter);


    }
}