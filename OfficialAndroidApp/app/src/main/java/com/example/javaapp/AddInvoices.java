package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaapp.database.DatabaseHelper;
import com.example.javaapp.database.InvoiceModel;

public class AddInvoices extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, viewInvoiceBtn, addClassBtn, viewClassBtn, addInvoiceToDbButton;

    EditText clientID, className, classYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoices);

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

        addInvoiceToDbButton = findViewById(R.id.addInvoiceToDbBtn);
        addInvoiceToDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseHelper db = new DatabaseHelper(AddInvoices.this);
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
            }
        });
    }
}