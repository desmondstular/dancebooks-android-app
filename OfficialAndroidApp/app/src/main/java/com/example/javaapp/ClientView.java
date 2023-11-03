package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
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
        //----------------list view, and custom search initializers---------------------------------
        lv_clientList = findViewById(R.id.lv_clientList);
        databaseDao = new DatabaseDao(ClientView.this);
        clientModelList = databaseDao.getAllClients();
        clientArrayAdapter = new ArrayAdapter<ClientModel>(ClientView.this,
                android.R.layout.simple_list_item_1, clientModelList);
        lv_clientList.setAdapter(clientArrayAdapter);
        searchClientBtn = findViewById(R.id.searchClientBtn);
        searchClientEmail = findViewById(R.id.searchClientEmail);
        searchClientFirstName = findViewById(R.id.searchClientFirstName);
        searchClientLastName = findViewById(R.id.searchClientLastName);
        //-------------search button on click---------------------------------------------------
        searchClientBtn.setOnClickListener(view -> {
            try {
                clientModelList.clear();
                if (searchClientLastName.getText().toString().isEmpty() &&
                        searchClientFirstName.getText().toString().isEmpty() &&
                        searchClientEmail.getText().toString().isEmpty())
                {
                    clientModelList = databaseDao.getAllClients();
                }
                else if (searchClientLastName.getText().toString().isEmpty() &&
                        searchClientFirstName.getText().toString().isEmpty())
                {
                    clientModelList.add(databaseDao.getOneClientByPrimaryKey(
                            searchClientEmail.getText().toString()));
                }
                else
                {
                    clientModelList = databaseDao.getAllClientByFirstNameAndOrLastName(
                            searchClientFirstName.getText().toString(),
                            searchClientLastName.getText().toString());
                }
                clientArrayAdapter = new ArrayAdapter<>(ClientView.this,
                        android.R.layout.simple_list_item_1, clientModelList);
                lv_clientList.setAdapter(clientArrayAdapter);
            }
            catch (Exception e)
            {
                Toast.makeText(ClientView.this, "Not Found",
                        Toast.LENGTH_SHORT).show();
            }
            searchClientEmail.getText().clear();
            searchClientFirstName.getText().clear();
            searchClientLastName.getText().clear();
            // hide keyboard on click
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
        //------------------------------------------------------------------------------------------
        //----------on click for show all owing-----------------------------------------------------
        showAllOwing = findViewById(R.id.showAllOwing);
        showAllOwing.setOnClickListener(view -> {
            try
            {
                clientModelList.clear();
                clientModelList = databaseDao.getAllClientWithBalanceGreaterThanZero();
                clientArrayAdapter = new ArrayAdapter<>(ClientView.this,
                        android.R.layout.simple_list_item_1, clientModelList);
                lv_clientList.setAdapter(clientArrayAdapter);

            }
            catch (Exception e)
            {
                Toast.makeText(ClientView.this, "None found",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //------------------------------------------------------------------------------------------
    }
}
