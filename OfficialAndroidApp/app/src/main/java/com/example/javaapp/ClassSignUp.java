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
                //clientModel = clients.get(position);
                clientSelected = clientsForSpinner.get(position);
                String[] inputString = clientSelected.split(",");
                clientSelected = inputString[2].trim();
                Toast.makeText(ClassSignUp.this, clientSelected,
                        Toast.LENGTH_LONG).show();
                //clientSelected = clientSelected.split()


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
                //classModel = dClass.get(position);
                classModelSelected = classModelList.get(position);
                className = classModelSelected.getClassName();
                classYear = classModelSelected.getYear();
//                Toast.makeText(ClassSignUp.this, className + " " + classYear,
//                        Toast.LENGTH_SHORT).show();
//                Toast.makeText(ClassSignUp.this, classModelSelected.toString(),
//                        Toast.LENGTH_SHORT).show();
//                String[] selected = classSelected.split(",");
//                className = selected[0];
//                classYear = selected[1];
//                String[] selected = classSelected.split(",");
//                className = selected[0];
//                classYear = selected[1].trim();
//
//
//                Toast.makeText(ClassSignUp.this, "1" + classYear + "1", Toast.LENGTH_LONG).show();

//                if (selected.length >= 2) {
//                    className = selected[0].trim();
//                    classYear = selected[1].trim();
//                }


//                Toast.makeText(ClassSignUp.this, selected.length,
//                        Toast.LENGTH_LONG).show();

                //ClientModel client = databaseHelper.getOneClientByName(first, last);
                //ClientModel client  = clients.get(position);
                //Toast.makeText(AddInvoices.this, selectedClass, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //sign up based on spinner sections-----------------------------------------------------

//        clientModel = databaseDao.getOneClientByPrimaryKey(clientSelected);
//        classModel = databaseDao.getOneClassByPrimaryKey(className, classYear);


        signUpBtn.setOnClickListener(view -> {
//            Toast.makeText(ClassSignUp.this, clientSelected + " " + className
//                    + " " + classYear,Toast.LENGTH_SHORT).show();
//            signedUpModel = new SignedUpModel(clientSelected, className,
//                    classYear, 0);
//            //Toast.makeText(ClassSignUp.this, signedUpModel.toString(),Toast.LENGTH_SHORT).show();
//            boolean isAdded = databaseDao.addOneSignedUp(signedUpModel);
//            Toast.makeText(ClassSignUp.this, Boolean.toString(isAdded),Toast.LENGTH_SHORT).show();

            try {
                signedUpModel = new SignedUpModel(clientSelected, className,
                        classYear, 0);
                Toast.makeText(ClassSignUp.this, signedUpModel.toString(),Toast.LENGTH_SHORT).show();
//                Toast.makeText(AddInvoices.this, danceClassModel.toString(),
//                        Toast.LENGTH_SHORT).show();
                Boolean isAdded = databaseDao.addOneSignedUp(signedUpModel);
                if (isAdded) {
                    Toast.makeText(ClassSignUp.this, signedUpModel.toString(),
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
    private List<String> getAllClientStrings(
            List<com.example.javaapp.database_v2.ClientModel> clientModelList){
        List<String> clientsForSpinner = new ArrayList<>();
        for(int i = 0; i < clientModelList.size(); i++){
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