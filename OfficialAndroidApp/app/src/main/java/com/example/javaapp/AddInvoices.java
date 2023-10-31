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

import com.example.javaapp.database_v1.ClientModel;
import com.example.javaapp.database_v1.DanceClassModel;
import com.example.javaapp.database_v1.DatabaseHelper;
import com.example.javaapp.database_v1.InvoiceModel;

import java.util.List;

public class AddInvoices extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, viewInvoiceBtn, addClassBtn, viewClassBtn, addInvoiceToDbButton;
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
            // Start the ClientView activity
            Intent intent = new Intent(AddInvoices.this, MainActivity.class);
            startActivity(intent);
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(AddInvoices.this, DanceClassView.class);
            startActivity(intent);
        });
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(AddInvoices.this, ClientView.class);
            startActivity(intent);
        });
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(AddInvoices.this, AddClient.class);
            startActivity(intent);
            //finish(); // Optional: Close this activity if you don't want to keep it in the back stack
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AddInvoices.this, InvoiceView.class);
            startActivity(intent);
        });
        //Navigation Bar END************************************************************************
        //------------------------------------------------------------------------------------------

        //Client Spinner setup
        clientSpinner = findViewById(R.id.clientSpinner);
        DatabaseHelper databaseHelper = new DatabaseHelper(AddInvoices.this);
        List<ClientModel> clients = databaseHelper.getAllClients();
        //List<String> clientNamesString = databaseHelper.getAllClientNames();

        ArrayAdapter<ClientModel> clientAdapter = new ArrayAdapter<>(AddInvoices.this,
                android.R.layout.simple_spinner_item, clients);

        clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientSpinner.setAdapter(clientAdapter);
        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id){
                clientModel = clients.get(position);
                //String selectedClient = clients.get(position).getClientFullName();
                //ClientModel client = databaseHelper.getOneClientByName(first, last);
                //ClientModel client  = clients.get(position);
                //Toast.makeText(AddInvoices.this, selectedClient, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Dance Class Spinner setup
        classSpinner = findViewById(R.id.classSpinner);

        List<DanceClassModel> dClass = databaseHelper.getAllDanceClasses();
        //List<String> clientNamesString = databaseHelper.getAllClientNames();

        ArrayAdapter<DanceClassModel> danceAdapter = new ArrayAdapter<>(AddInvoices.this,
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










        addInvoiceToDbButton = findViewById(R.id.addInvoiceToDbBtn);
//        addInvoiceToDbButton.setOnClickListener(view -> {
//            databaseHelper.addOneInvoice(invoiceModel);
//        });
        addInvoiceToDbButton.setOnClickListener(view -> {
            try {
//                Toast.makeText(AddInvoices.this, danceClassModel.toString(),
//                        Toast.LENGTH_SHORT).show();
                invoiceModel = new InvoiceModel(clientModel.getClientID(), danceClassModel.getClassName(),
                        danceClassModel.getClassYear());
                Boolean isAdded = databaseHelper.addOneInvoice(invoiceModel);
                if (isAdded) {
                    Toast.makeText(AddInvoices.this, invoiceModel.toString(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddInvoices.this, "Unsuccessful",
                            Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(AddInvoices.this , invoiceModel.toString(),
//                        Toast.LENGTH_SHORT).show();
                //DatabaseHelper db = new DatabaseHelper(AddInvoices.this);
                /*InvoiceModel invoiceModel = new InvoiceModel();
                Boolean isAdded = db.deleteOneInvoice(invoiceModel);
                if (isAdded) {
                    //Will add code to erase all text boxes.
                } else {
                    Toast.makeText(AddInvoices.this, "Unsuccessful",
                            Toast.LENGTH_SHORT).show();
                }*/
            } catch (Exception e) {
                Toast.makeText(AddInvoices.this, "Error Creating Invoice",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}