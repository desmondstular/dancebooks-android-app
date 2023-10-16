package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ClientView extends AppCompatActivity {
    Button addClientsBtn, addClassBtn, addInvoiceBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
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


        //------------------------------------------------------------------------------------------
    }
}
