package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaapp.database.DanceClassModel;
import com.example.javaapp.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button addClassBtn, addClientsBtn, viewClientsBtn, viewClassBtn, addInvoiceBtn, viewInvoiceBtn;
    EditText ClName, ClYear, An_Price, Bi_Price, Mn_Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------------------------------
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(MainActivity.this, AddClient.class);
            startActivity(intent);
            //finish(); // Close this activity if you don't want to keep it in the back stack
        });
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            // Start the ClientView activity
            Intent intent = new Intent(MainActivity.this, ClientView.class);
            startActivity(intent);
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(MainActivity.this, DanceClassView.class);
            startActivity(intent);
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            //Start the viewClass Activity
            Intent intent = new Intent(MainActivity.this, AddInvoices.class);
            startActivity(intent);
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, InvoiceView.class);
            startActivity(intent);
        });
        //------------------------------------------------------------------------------------------

        addClassBtn = findViewById(R.id.btnAddClass);
        ClName = findViewById(R.id.Cl_Name);
        ClYear = findViewById(R.id.Cl_Year);
        An_Price = findViewById(R.id.An_Price);
        Bi_Price = findViewById(R.id.BI_Price);
        Mn_Price = findViewById(R.id.Mn_Price);

        //btn listeners
        //btn_Add_Class.set

        addClassBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v
             * Purpose:
             * Gets the input from the user OnClick of "AddClass" btn
             * and creates the Dance Class Object, then displays the toString() of the Dance Class
             *
             */
            @Override
            public void onClick(View v) {
                try {
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                    DanceClassModel danceClassModel = new DanceClassModel(ClName.getText().toString(),
                            Integer.parseInt(ClYear.getText().toString()),
                            Integer.parseInt(An_Price.getText().toString()),
                            Integer.parseInt(Bi_Price.getText().toString()),
                            Integer.parseInt(Mn_Price.getText().toString()));

                    boolean i = databaseHelper.addOneDanceClass(danceClassModel);
                    if (!i) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        ClName.getText().clear();
                        ClYear.getText().clear();
                        An_Price.getText().clear();
                        Bi_Price.getText().clear();
                        Mn_Price.getText().clear();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error Creating Dance Class",
                            Toast.LENGTH_SHORT).show();

                }
            }
            //--------------------------------------------------------------------------------------

        });
        //------------------------------------------------------------------------------------------

    }
}