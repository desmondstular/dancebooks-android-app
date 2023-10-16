package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddInvoices extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, viewInvoicesBtn, addClassBtn, viewClassBtn;

    EditText clientID, className, classYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoices);

    }
}