package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClientView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);

        // Set click listener for Add Clients button
        Button addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to MainActivity
                Intent intent = new Intent(ClientView.this, AddClient.class);
                startActivity(intent);
                finish(); // Optional: Close this activity if you don't want to keep it in the back stack
            }
        });

        // Set click listener for View Clients button
        Button viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the ClientView activity
                Intent intent = new Intent(ClientView.this, ClientView.class);
                startActivity(intent);
            }
        });
    }
}
