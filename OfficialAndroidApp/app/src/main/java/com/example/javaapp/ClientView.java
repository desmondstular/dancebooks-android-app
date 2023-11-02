package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;

import java.util.List;

public class ClientView extends AppCompatActivity {
    Button addClientsBtn, addClassBtn, addInvoiceBtn,viewInvoiceBtn, viewClassBtn,
            searchClientBtn, showAllOwing;
    EditText searchClientEmail, searchClientLastName, searchClientFirstName;
    List<ClientModel> clientModelList;
    ArrayAdapter<ClientModel> clientArrayAdapter;
    ListView lv_clientList;
    DatabaseDao databaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view);
        lv_clientList = findViewById(R.id.lv_clientList);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start**********************************************************************
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, AddClient.class));
        });
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, MainActivity.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, ClassSignUp.class));
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, SignUpView.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, DanceClassView.class));
        });
        //Navigation Bar End ***********************************************************************
        databaseDao = new DatabaseDao(ClientView.this);
        clientModelList = databaseDao.getAllClients();
        clientArrayAdapter = new ArrayAdapter<ClientModel>(ClientView.this,
                android.R.layout.simple_list_item_1, clientModelList);
        lv_clientList.setAdapter(clientArrayAdapter);
        //-------------search button on click---------------------------------------------------
        searchClientBtn = findViewById(R.id.searchClientBtn);
        searchClientEmail = findViewById(R.id.searchClientEmail);
        searchClientFirstName = findViewById(R.id.searchClientFirstName);
        searchClientLastName = findViewById(R.id.searchClientLastName);
        searchClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText((Context) ClientView.this, (CharSequence) databaseDao.getOneClientByPrimaryKey(
//                                searchClientEmail.getText().toString()),
//                        Toast.LENGTH_LONG).show();
                try {
                    // Create client data object from getting info from text boxes
                    if (searchClientLastName.getText().toString().isEmpty() &&
                            searchClientFirstName.getText().toString().isEmpty()){
                        clientModelList.clear();
                        clientModelList.add(databaseDao.getOneClientByPrimaryKey(
                                searchClientEmail.getText().toString()));

                        clientArrayAdapter = new ArrayAdapter<ClientModel>(ClientView.this,
                                android.R.layout.simple_list_item_1, clientModelList);
                        lv_clientList.setAdapter(clientArrayAdapter);
                    }
//                    boolean i = databaseDao.addOneClient(clientModel);
//                    if (!i) {
//                        Toast.makeText(AddClient.this, "Failed", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(AddClient.this, "Successfully added", Toast.LENGTH_SHORT).show();
//                        clientFirstNameTextBox.getText().clear();
//                        clientLastNameTextBox.getText().clear();
//                        clientEmailTextBox.getText().clear();
//                        clientPhoneNumberTextBox.getText().clear();
//                    }
                } catch (Exception e) {
                    Toast.makeText(ClientView.this, "Not Found",
                            Toast.LENGTH_SHORT).show();
                }
                searchClientEmail.getText().clear();
                searchClientFirstName.getText().clear();
                searchClientLastName.getText().clear();

            }
        });
        //------------------------------------------------------------------------------------------
    }
}
