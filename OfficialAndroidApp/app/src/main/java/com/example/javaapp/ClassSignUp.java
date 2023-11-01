package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.javaapp.database.ClientModel;
import com.example.javaapp.database.DanceClassModel;
import com.example.javaapp.database.DatabaseHelper;
import com.example.javaapp.database.InvoiceModel;

import java.util.List;

public class ClassSignUp extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, viewInvoiceBtn, addClassBtn, viewClassBtn, signUpBtn;
    Spinner clientSpinner, classSpinner;
    DanceClassModel danceClassModel;
    ClientModel clientModel;
    InvoiceModel invoiceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoices);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start**********************************************************************
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, MainActivity.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, DanceClassView.class));
        });
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, ClientView.class));
        });
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, AddClient.class));
            //finish(); // Optional: Close this activity if you don't want to keep it in the back stack
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, InvoiceView.class));
        });
        //Navigation Bar END************************************************************************

        //======================================================================================
        //Client Spinner setup------------------------------------------------------------------
        clientSpinner = findViewById(R.id.clientSpinner);
        DatabaseHelper databaseHelper = new DatabaseHelper(ClassSignUp.this);
        List<ClientModel> clients = databaseHelper.getAllClients();
        ArrayAdapter<ClientModel> clientAdapter = new ArrayAdapter<>(ClassSignUp.this,
                android.R.layout.simple_spinner_item, clients);

        clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientSpinner.setAdapter(clientAdapter);
        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id){
                clientModel = clients.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //Dance Class Spinner setup-------------------------------------------------------------
        classSpinner = findViewById(R.id.classSpinner);
        List<DanceClassModel> dClass = databaseHelper.getAllDanceClasses();
        ArrayAdapter<DanceClassModel> danceAdapter = new ArrayAdapter<>(ClassSignUp.this,
                android.R.layout.simple_spinner_item, dClass);
        danceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(danceAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id){
                danceClassModel = dClass.get(position);
                //ClientModel client = databaseHelper.getOneClientByName(first, last);
                //ClientModel client  = clients.get(position);
                //Toast.makeText(AddInvoices.this, selectedClass, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //sign up based on spinner sections-----------------------------------------------------
        signUpBtn = findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(view -> {
            try {
//                Toast.makeText(AddInvoices.this, danceClassModel.toString(),
//                        Toast.LENGTH_SHORT).show();
                invoiceModel = new InvoiceModel(clientModel.getClientID(), danceClassModel.getClassName(),
                        danceClassModel.getClassYear());
                Boolean isAdded = databaseHelper.addOneInvoice(invoiceModel);
                if (isAdded) {
                    Toast.makeText(ClassSignUp.this, invoiceModel.toString(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ClassSignUp.this, "Unsuccessful",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(ClassSignUp.this, "Error Creating Invoice",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}