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
import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;

public class MainActivity extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button addClassBtn, addClientsBtn, viewClientsBtn, viewClassBtn, addInvoiceBtn, viewInvoiceBtn;
    EditText ClName, ClYear, An_Price, classCapacity;
    DatabaseDao databaseDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start *********************************************************************
        // Set click listener for Add Clients button, and all other screens
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddClient.class));
            //finish(); // Close this activity if you don't want to keep it in the back stack
        });
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ClientView.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, DanceClassView.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ClassSignUp.class));
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUpView.class));
        });
        //Navigation Bar End ***********************************************************************
        //------------------------------------------------------------------------------------------

        addClassBtn = findViewById(R.id.btnAddClass);
        ClName = findViewById(R.id.Cl_Name);
        ClYear = findViewById(R.id.Cl_Year);
        An_Price = findViewById(R.id.An_Price);
        classCapacity = findViewById(R.id.classCapacity);



        addClassBtn.setOnClickListener(new View.OnClickListener() {
            /**@param v
             * Purpose: Gets the input from the user OnClick of "AddClass" btn
             * and creates the Dance Class Object, then displays the toString() of the Dance Class
             */
            @Override
            public void onClick(View v) {
                try {
//                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
//                    DanceClassModel danceClassModel = new DanceClassModel(
//                            ClName.getText().toString(),
//                            Integer.parseInt(ClYear.getText().toString()),
//                            Integer.parseInt(An_Price.getText().toString()));
                    databaseDao = new DatabaseDao(MainActivity.this);
                    ClassModel classModel = new ClassModel(
                            ClName.getText().toString(),
                            Integer.parseInt(ClYear.getText().toString()),
                            Integer.parseInt(An_Price.getText().toString()),
                            Integer.parseInt(classCapacity.getText().toString()),0);


                    boolean i = databaseDao.addOneClass(classModel);
                    if (!i) {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        ClName.getText().clear();
                        ClYear.getText().clear();
                        An_Price.getText().clear();
                        classCapacity.getText().clear();
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