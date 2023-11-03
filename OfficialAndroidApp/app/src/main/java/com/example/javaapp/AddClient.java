package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaapp.database.DatabaseHelper;
import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;

import java.math.BigInteger;

public class AddClient extends AppCompatActivity {
    Button viewClientsBtn, addClassBtn, clientAddButton, addInvoiceBtn, viewInvoiceBtn, viewClassBtn;
    EditText clientFirstNameTextBox, clientLastNameTextBox, clientEmailTextBox, clientPhoneNumberTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        //------------------------------------------------------------------------------------------
        // Initialize textbook objects by id
        clientFirstNameTextBox = findViewById(R.id.et_first_name);
        clientLastNameTextBox = findViewById(R.id.et_last_name);
        clientEmailTextBox = findViewById(R.id.et_email);
        clientPhoneNumberTextBox = findViewById(R.id.et_phone_number);

        //Navigation Bar Start *********************************************************************
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, ClientView.class));
        });
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, MainActivity.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, ClassSignUp.class));
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, SignUpView.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, DanceClassView.class));
        });
        //Navigation Bar End ***********************************************************************
        //------------------------------------------------------------------------------------------
        // Set listener for Add client button that adds client to database
        clientAddButton = findViewById(R.id.clientAddButton);
        clientAddButton.setOnClickListener(view -> {
            try {
                DatabaseDao databaseDao = new DatabaseDao(AddClient.this);
                ClientModel clientModel = new ClientModel(
                        clientEmailTextBox.getText().toString().toLowerCase(),
                        clientFirstNameTextBox.getText().toString().toUpperCase(),
                        clientLastNameTextBox.getText().toString().toUpperCase(),
                        clientPhoneNumberTextBox.getText().toString(),
                        0);

                boolean i = databaseDao.addOneClient(clientModel);
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
        });
    }
}

//    public static class InvoiceView extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//        }
//    }
//}