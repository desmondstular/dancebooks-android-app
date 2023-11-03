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

import com.example.javaapp.database.DanceClassModel;
import com.example.javaapp.database.DatabaseHelper;
import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;

import java.util.List;

public class DanceClassView extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button addClassBtn, addClientBtn, viewClientsBtn, addInvoiceBtn, viewInvoiceBtn,
            searchBtn, availableClassesBtn;
    EditText className, classYear;
    ListView lv_danceClassList;
    DatabaseDao databaseDao;
    ArrayAdapter<ClassModel> classModelArrayAdapter;
    List<ClassModel> classModelList;
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
        databaseDao = new DatabaseDao(DanceClassView.this);
        classModelList = databaseDao.getAllClasses();
        classModelArrayAdapter = new ArrayAdapter<>(DanceClassView.this,
                android.R.layout.simple_list_item_1, classModelList);
        lv_danceClassList.setAdapter(classModelArrayAdapter);
        //---------------init the fields for search----------------------------------
        searchBtn = findViewById(R.id.searchClassBtn);
        availableClassesBtn = findViewById(R.id.availableClassesBtn);
        classYear = findViewById(R.id.searchClassYear);
        className = findViewById(R.id.searchClassName);
        //--------------onclick for search----------------------------------------------------------
        searchBtn.setOnClickListener(view -> {
            classModelList.clear();
            if (className.getText().toString().isEmpty() &&
                    classYear.getText().toString().isEmpty()){
                classModelList = databaseDao.getAllClasses();
            }
            else if (classYear.getText().toString().isEmpty()){
                // add query for searching classTable by class name
                //classModelList = databaseDao.
            }
            else if (className.getText().toString().isEmpty()){
                try {
                    Integer classYearInt = Integer.parseInt(classYear.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(DanceClassView.this, "Invalid Year",
                            Toast.LENGTH_SHORT).show();
                }
                // add query for searching classTable by class year
                //classModelList = databaseDao.
            }
            else {
                int classYearInt = 0;
                try {
                    classYearInt = Integer.parseInt(classYear.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(DanceClassView.this, "Invalid Year",
                            Toast.LENGTH_SHORT).show();
                }
                classModelList.add(databaseDao.getOneClassByPrimaryKey(
                        className.getText().toString(),classYearInt));
            }
            classModelArrayAdapter = new ArrayAdapter<>(DanceClassView.this,
                    android.R.layout.simple_list_item_1, classModelList);
            lv_danceClassList.setAdapter(classModelArrayAdapter);
            if (classModelList.isEmpty()){
                Toast.makeText(DanceClassView.this, "No Classes found",
                        Toast.LENGTH_SHORT).show();
            }
            classYear.getText().clear();
            className.getText().clear();
            // hide keyboard on click
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
        //-------------on click for show all available btn------------------------------------------
        availableClassesBtn.setOnClickListener(view -> {
            try
            {
                classModelList.clear();
                classModelList = databaseDao.getAllAvailableClasses();
                classModelArrayAdapter = new ArrayAdapter<>(DanceClassView.this,
                        android.R.layout.simple_list_item_1, classModelList);
                lv_danceClassList.setAdapter(classModelArrayAdapter);
            }
            catch (Exception e)
            {
                Toast.makeText(DanceClassView.this, "None found",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}