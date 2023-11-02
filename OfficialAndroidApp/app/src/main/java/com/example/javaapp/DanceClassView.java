package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.javaapp.database.DanceClassModel;
import com.example.javaapp.database.DatabaseHelper;

import java.util.List;

public class DanceClassView extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button addClassBtn, addClientBtn, viewClientsBtn, addInvoiceBtn, viewInvoiceBtn;
    ListView lv_danceClassList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_class_view);
        lv_danceClassList = findViewById(R.id.lv_danceClassList);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start**********************************************************************
        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(DanceClassView.this, ClientView.class));
        });
        // Set click listener for Add Client button
        addClientBtn = findViewById(R.id.addClientsBtn);
        addClientBtn.setOnClickListener(view -> {
            startActivity(new Intent(DanceClassView.this, AddClient.class));
        });
        //Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener( view -> {
            startActivity(new Intent(DanceClassView.this, MainActivity.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(DanceClassView.this, ClassSignUp.class));
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(DanceClassView.this, SignUpView.class));
        });
        //Navigation Bar End************************************************************************
        //------------------------------------------------------------------------------------------
        //DATA BASE VIEW
        DatabaseHelper databaseHelper = new DatabaseHelper(DanceClassView.this);
        List<DanceClassModel> everyDanceClass = databaseHelper.getAllDanceClasses();
        ArrayAdapter<DanceClassModel> danceClassArrayAdapter = new ArrayAdapter<DanceClassModel>
                (DanceClassView.this, android.R.layout.simple_list_item_1, everyDanceClass);
        lv_danceClassList.setAdapter(danceClassArrayAdapter);







    }
}