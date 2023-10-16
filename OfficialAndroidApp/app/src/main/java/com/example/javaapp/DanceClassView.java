package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DanceClassView extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button addClassBtn, addClientBtn, viewClientsBtn, addInvoiceBtn, viewInvoiceBtn;
    RecyclerView danceClassList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_class_view);
        //------------------------------------------------------------------------------------------

        //Set click listener
        //TODO: Add  View Invoice Screen
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        //TODO: Add ADD Invoice Screen
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(DanceClassView.this, ClientView.class);
            startActivity(intent);
        });
        // Set click listener for Add Client button
        addClientBtn = findViewById(R.id.addClientsBtn);
        addClientBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DanceClassView.this, AddClient.class);
            startActivity(intent);
        });
        //Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener( view -> {
            // Start the Client View activity
            Intent intent = new Intent(DanceClassView.this, MainActivity.class);
            startActivity(intent);
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(DanceClassView.this, AddInvoices.class);
            startActivity(intent);
        });







    }
}