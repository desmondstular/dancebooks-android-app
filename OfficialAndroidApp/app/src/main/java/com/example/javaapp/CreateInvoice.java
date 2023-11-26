package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.database_v2.InvoiceModel;
import com.example.javaapp.database_v2.SignedUpModel;

import java.util.ArrayList;
import java.util.List;

public class CreateInvoice extends AppCompatActivity {
    Button generateInvoice;
    Spinner clientSpinner;
    ClientModel clientModel;
    DatabaseDao databaseDao;
    List<com.example.javaapp.database_v2.ClientModel> clientModelList;
    ArrayAdapter<String> clientAdapter;
    List<String> clientsForSpinner;
    String clientSelected;
    InvoiceModel invoiceModel;
    LinearLayout goHomeClick, viewInvoiceClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);
        //----------------navigation code-----------------------------------------------------------
        viewInvoiceClick = findViewById(R.id.viewInvoiceClick);
//        viewInvoiceClick.setOnClickListener(view -> {
//            startActivity(new Intent(CreateInvoice.this, ViewInvoice.class));
//        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(CreateInvoice.this, HomePage.class));
        });
        //------------------------------------------------------------------------------------------

        clientSpinner = findViewById(R.id.clientSpinner);
        databaseDao = new DatabaseDao(CreateInvoice.this);
        clientModelList = databaseDao.getAllClients();
        clientsForSpinner = getAllClientStrings(clientModelList);
        clientAdapter = new ArrayAdapter<>(CreateInvoice.this,
                android.R.layout.simple_spinner_item, clientsForSpinner);
        clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientSpinner.setAdapter(clientAdapter);
        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.GRAY);
                clientSelected = clientsForSpinner.get(position);
                String[] inputString = clientSelected.split(",");
                clientSelected = inputString[2].trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //------------------------------------------------------------------------------------------
        generateInvoice = findViewById(R.id.generateInvoice);
        generateInvoice.setOnClickListener(view -> {
            try {
                Toast.makeText(CreateInvoice.this, clientSelected, Toast.LENGTH_SHORT).show();
                //clientSelected = string, format "name@email.com"
                invoiceModel = new InvoiceModel((Integer) null, clientSelected, (float) 0.0, 0);
                // update sign ups associated with this email: where invoiceID=Null
                Boolean isAdded = databaseDao.addOneInvoice(invoiceModel);
                if (isAdded) {
                    Toast.makeText(CreateInvoice.this, "Successfully added",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CreateInvoice.this, "Unsuccessful",
                            Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(CreateInvoice.this, "Error Creating Invoice",
                    Toast.LENGTH_SHORT).show();
            }

        });
    }
    private List<String> getAllClientStrings(
            List<com.example.javaapp.database_v2.ClientModel> clientModelList){
        List<String> clientsForSpinner = new ArrayList<>();
        for (int i = 0; i < clientModelList.size(); i++) {
            clientsForSpinner.add(clientModelList.get(i).getFnameLnameEmail());
        }
        return clientsForSpinner;
    }
}