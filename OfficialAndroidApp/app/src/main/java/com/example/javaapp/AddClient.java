package com.example.javaapp;

import static com.example.javaapp.helpers.Clean.cleanEmail;
import static com.example.javaapp.helpers.Clean.cleanName;
import static com.example.javaapp.helpers.Clean.cleanPhone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.helpers.Clean;

public class AddClient extends AppCompatActivity {
    Button clientAddButton;
    LinearLayout goHomeClick, viewClientsClick;
    EditText clientFirstNameTextBox, clientLastNameTextBox, clientEmailTextBox, clientPhoneNumberTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        //Navigation Bar Start *********************************************************************
        // Set click listener for View Clients button
        viewClientsClick = findViewById(R.id.viewClientsClick);
        viewClientsClick.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, ClientView.class));
        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(AddClient.this, HomePage.class));
        });
        //Navigation Bar End ***********************************************************************
        //------------------------------------------------------------------------------------------
        //--------------------ADD CLIENT FUNCTIONALITY----------------------------------------------
        // Initialize textbook objects by id
        clientFirstNameTextBox = findViewById(R.id.et_first_name);
        clientLastNameTextBox = findViewById(R.id.et_last_name);
        clientEmailTextBox = findViewById(R.id.et_email);
        clientPhoneNumberTextBox = findViewById(R.id.et_phone_number);
        clientAddButton = findViewById(R.id.clientAddButton);
        // Set listener for Add client button that adds client to database
        clientAddButton.setOnClickListener(view -> {
            if (clientEmailTextBox.getText().toString().isEmpty() ||
                    clientFirstNameTextBox.getText().toString().isEmpty() ||
                    clientLastNameTextBox.getText().toString().isEmpty() ||
                    clientPhoneNumberTextBox.getText().toString().isEmpty()) {
                Toast.makeText(AddClient.this, "Please Fill Out All Fields", Toast.LENGTH_SHORT).show();
            }
            else
            {
                try {
                    DatabaseDao databaseDao = new DatabaseDao(AddClient.this);
                    ClientModel clientModel = new ClientModel(
                            cleanEmail(clientEmailTextBox.getText().toString().toLowerCase()),
                            cleanName(clientFirstNameTextBox.getText().toString().toUpperCase()),
                            cleanName(clientLastNameTextBox.getText().toString().toUpperCase()),
                            cleanPhone(clientPhoneNumberTextBox.getText().toString()),
                            0);

                    boolean i = databaseDao.addOneClient(clientModel);
                    if (!i) {
                        Toast.makeText(AddClient.this, "Invalid Email, Already Exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddClient.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        clientFirstNameTextBox.getText().clear();
                        clientLastNameTextBox.getText().clear();
                        clientEmailTextBox.getText().clear();
                        clientPhoneNumberTextBox.getText().clear();
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(AddClient.this, "Error Creating Client",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
