package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.ClientModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.database_v2.SignedUpModel;

import java.util.ArrayList;
import java.util.List;

public class ClassSignUp extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, viewInvoiceBtn, addClassBtn, viewClassBtn, signUpBtn;
    Spinner clientSpinner, classSpinner;
    ClassModel classModel, classModelSelected;
    ClientModel clientModel;
    SignedUpModel signedUpModel;
    DatabaseDao databaseDao;
    List<com.example.javaapp.database_v2.ClientModel> clientModelList;
    ArrayAdapter<String> clientAdapter;
    ArrayAdapter<ClassModel> classAdapter;
    List<String> clientsForSpinner, classesForSpinner;
    String clientSelected, classSelected, className;
    List<ClassModel> classModelList;
    int classYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_sign_up);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start**********************************************************************
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, AddClass.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, DanceClassView.class));
        });
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, ClientView.class));
        });
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, AddClient.class));
            //finish();
        });
        viewInvoiceBtn = findViewById(R.id.viewInvoiceBtn);
        viewInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(ClassSignUp.this, SignUpView.class));
        });
        //Navigation Bar END************************************************************************
        //==========================================================================================
        //Client Spinner setup----------------------------------------------------------------------
        clientSpinner = findViewById(R.id.clientSpinner);
//        DatabaseHelper databaseHelper = new DatabaseHelper(ClassSignUp.this);
        databaseDao = new DatabaseDao(ClassSignUp.this);
        clientModelList = databaseDao.getAllClients();
        clientsForSpinner = getAllClientStrings(clientModelList);

        clientAdapter = new ArrayAdapter<>(ClassSignUp.this,
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
        //Dance Class Spinner setup-------------------------------------------------------------
        classSpinner = findViewById(R.id.classSpinner);
        signUpBtn = findViewById(R.id.signUpBtn);

        classModelList = databaseDao.getAllClasses();
        //classesForSpinner = getAllClassString(classModelList);
        classAdapter = new ArrayAdapter<>(ClassSignUp.this,
                android.R.layout.simple_spinner_item, classModelList);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.GRAY);
                classModelSelected = classModelList.get(position);
                className = classModelSelected.getClassName();
                classYear = classModelSelected.getYear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //sign up based on spinner sections-----------------------------------------------------
        signUpBtn.setOnClickListener(view -> {
            try {
                signedUpModel = new SignedUpModel(clientSelected, className,
                        classYear, 0);
                Boolean isAdded = databaseDao.addOneSignedUp(signedUpModel);
                if (isAdded) {
                    Toast.makeText(ClassSignUp.this, "Successfully added",
                            Toast.LENGTH_LONG).show();
                    classModelList = databaseDao.getAllClasses();
                    //classesForSpinner = getAllClassString(classModelList);
                    classAdapter = new ArrayAdapter<>(ClassSignUp.this,
                            android.R.layout.simple_spinner_item, classModelList);
                    classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    classSpinner.setAdapter(classAdapter);

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
    private List<String> getAllClientStrings(
            List<com.example.javaapp.database_v2.ClientModel> clientModelList){
        List<String> clientsForSpinner = new ArrayList<>();
        for (int i = 0; i < clientModelList.size(); i++) {
            clientsForSpinner.add(clientModelList.get(i).getFnameLnameEmail());
        }
        return clientsForSpinner;
    }
    private List<String> getAllClassString(
            List<ClassModel> classModelList){
        List<String> classesForSpinner = new ArrayList<>();
        for(int i = 0; i < classModelList.size(); i++){
            classesForSpinner.add(classModelList.get(i).nameYearAvailability());
        }
        return classesForSpinner;
    }
}