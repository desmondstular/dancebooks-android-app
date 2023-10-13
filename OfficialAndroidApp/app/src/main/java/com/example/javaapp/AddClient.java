package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.Button;

public class AddClient extends AppCompatActivity {
    Button viewClientsBtn, addClassBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        //------------------------------------------------------------------------------------------
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(AddClient.this, ClientView.class);
            startActivity(intent);
        });
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(AddClient.this, MainActivity.class);
            startActivity(intent);
        });
        //------------------------------------------------------------------------------------------
    }
}