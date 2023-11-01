package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaapp.database.ClientModel;
import com.example.javaapp.database.DatabaseHelper;

public class AddClient extends AppCompatActivity {
    Button viewClientsBtn, addClassBtn, clientAddButton, addInvoiceBtn, viewInvoiceBtn, viewClassBtn;
    EditText clientFirstNameTextBox, clientLastNameTextBox, clientEmailTextBox, clientPhoneNumberTextBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        //------------------------------------------------------------------------------------------
        // Initialize textbox objects by id
        clientFirstNameTextBox = findViewById(R.id.et_first_name);
        clientLastNameTextBox = findViewById(R.id.et_last_name);
        clientEmailTextBox = findViewById(R.id.et_email);
        clientPhoneNumberTextBox = findViewById(R.id.et_phone_number);

        //Navigation Bar Start *********************************************************************
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
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(AddClient.this, ClassSignUp.class);
            startActivity(intent);
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AddClient.this, SignUpView.class);
            startActivity(intent);
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(AddClient.this, DanceClassView.class);
            startActivity(intent);
        });
        //Navigation Bar End ***********************************************************************
        //------------------------------------------------------------------------------------------
        // Set listener for Add client button that adds client to database
        clientAddButton = findViewById(R.id.clientAddButton);
        clientAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseHelper databaseHelper = new DatabaseHelper(AddClient.this);
                    // Create client data object from getting info from text boxes
                    ClientModel clientModel = new ClientModel(0,
                            clientFirstNameTextBox.getText().toString(),
                            clientLastNameTextBox.getText().toString(),
                            clientEmailTextBox.getText().toString(),
                            Integer.parseInt(clientPhoneNumberTextBox.getText().toString()));

                    boolean i = databaseHelper.addOneClient(clientModel);
                    if (!i) {
                        Toast.makeText(AddClient.this, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddClient.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        clientFirstNameTextBox.getText().clear();
                        clientLastNameTextBox.getText().clear();
                        clientEmailTextBox.getText().clear();
                        clientPhoneNumberTextBox.getText().clear();
                    }
                } catch (Exception e) {
                    Toast.makeText(AddClient.this, "Error Creating Client",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static class InvoiceView extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    }
}