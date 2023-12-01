package com.example.javaapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.helpers.Clean;

import java.util.List;

public class ClientView extends AppCompatActivity {
    Button searchClientBtn, showAllOwing;
    LinearLayout addClientClick, goHomeClick;
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
        addClientClick = findViewById(R.id.addClientClick);
        addClientClick.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, AddClient.class));
        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(ClientView.this, HomePage.class));
        });
        //Navigation Bar End ***********************************************************************
        //----------------list view, and custom search initializers---------------------------------
        lv_clientList = findViewById(R.id.lv_clientList);
        databaseDao = new DatabaseDao(ClientView.this);
        clientModelList = databaseDao.getAllClients();
        clientArrayAdapter = new ClientViewAdapter(ClientView.this, clientModelList);
        lv_clientList.setAdapter(clientArrayAdapter);
        //---------------init the fields for search----------------------------------
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
                            Clean.cleanEmail(searchClientEmail.getText().toString().toUpperCase())));
                }
                else
                {
                    clientModelList = databaseDao.getAllClientByFirstNameAndOrLastName(
                            searchClientFirstName.getText().toString().toUpperCase(),
                            searchClientLastName.getText().toString().toUpperCase());
                }
                clientArrayAdapter = new ClientViewAdapter(ClientView.this, clientModelList);
                lv_clientList.setAdapter(clientArrayAdapter);
                if (clientModelList.isEmpty()){
                    Toast.makeText(ClientView.this, "No Clients found",
                            Toast.LENGTH_SHORT).show();
                }
                searchClientLastName.getText().clear();
                searchClientEmail.getText().clear();
                searchClientFirstName.getText().clear();
            }
            catch (Exception e)
            {
                Toast.makeText(ClientView.this, "Invalid Search",
                        Toast.LENGTH_SHORT).show();
            }
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
                clientArrayAdapter = new ClientViewAdapter(ClientView.this, clientModelList);
                lv_clientList.setAdapter(clientArrayAdapter);
                //-----------------------------------------------------------
                searchClientLastName.getText().clear();
                searchClientEmail.getText().clear();
                searchClientFirstName.getText().clear();
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
