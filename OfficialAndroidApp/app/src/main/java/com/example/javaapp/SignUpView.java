package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.javaapp.database.DatabaseHelper;
import com.example.javaapp.database.InvoiceModel;

import java.util.List;

public class SignUpView extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, addInvoiceBtn, addClassBtn, viewClassBtn;

    ListView lv_Invoice_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);
        lv_Invoice_List = findViewById(R.id.lv_Invoice_list);
        //Navigation Bar Start *********************************************************************
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, MainActivity.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, ClassSignUp.class));
        });
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, AddClient.class));
        });
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, ClientView.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, DanceClassView.class));
        });
        //Navigation Bar End ***********************************************************************

        DatabaseHelper databaseHelper = new DatabaseHelper(SignUpView.this);
        List<InvoiceModel> everyInvoice = databaseHelper.getAllInvoices();
        ArrayAdapter<InvoiceModel> invoiceArrayAdapter = new ArrayAdapter<InvoiceModel>(SignUpView.this,
                android.R.layout.simple_list_item_1, everyInvoice);
        lv_Invoice_List.setAdapter(invoiceArrayAdapter);
    }
}