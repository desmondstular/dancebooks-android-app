package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.database_v2.SignedUpModel;
import com.example.javaapp.helpers.Clean;

import java.util.List;

public class SignUpView extends AppCompatActivity {
    Button searchSignupsButton;
    ListView lv_SignedUp_List;
    EditText emailAddress, className, classYear;
    DatabaseDao databaseDao;
    ArrayAdapter<SignedUpModel> signedUpModelArrayAdapter;
    List<SignedUpModel> signedUpModelList;
    LinearLayout signUpClick, goHomeClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);
        lv_SignedUp_List = findViewById(R.id.lv_Invoice_list);
        //Navigation Bar Start *********************************************************************
        signUpClick = findViewById(R.id.signUpClick);
        signUpClick.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, ClassSignUp.class));
        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, HomePage.class));
        });
        //Navigation Bar End ***********************************************************************

        //DataBase View-----------------------------------------------------------------------------
        databaseDao = new DatabaseDao(SignUpView.this);
        signedUpModelList = databaseDao.getAllSignedUps();
        signedUpModelArrayAdapter = new SignUpAdapter(SignUpView.this, signedUpModelList);
        lv_SignedUp_List.setAdapter(signedUpModelArrayAdapter);
        //--------------------init the fields for search--------------------------------------------
        searchSignupsButton = findViewById(R.id.search_sign_ups);
        emailAddress = findViewById(R.id.search_client_email_sign_up);
        className = findViewById(R.id.search_class_name);
        classYear = findViewById(R.id.search_class_year);
        //------------------------------------------------------------------------------------------
        //Changes to View Based on Search Criteria
        // NEED TO REFACTOR/DELETE IS PAID SEARCH CRITERIA FOR SIGN UPS
        searchSignupsButton.setOnClickListener(view -> {
            signedUpModelList.clear();
            int classYearInt;
            if (classYear.getText().toString().isEmpty()){
                classYearInt = 0;
            }
            else{
                classYearInt = Integer.parseInt(classYear.getText().toString());
            }
            signedUpModelList = databaseDao.getFromTextFields(
                    Clean.cleanEmail(emailAddress.getText().toString()),
                    className.getText().toString().toUpperCase(),
                    classYearInt,
                    -1);
            // MIGHT NEED TO REFACTOR getFromTextFields, isPaid is no longer a needed search criteria
            signedUpModelArrayAdapter = new ArrayAdapter<>(SignUpView.this,
                    android.R.layout.simple_list_item_1, signedUpModelList);
            lv_SignedUp_List.setAdapter(signedUpModelArrayAdapter);
            // hide keyboard on click
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            if (signedUpModelList.isEmpty()){
                Toast.makeText(SignUpView.this, "No Signups found",
                        Toast.LENGTH_SHORT).show();
            }
            classYear.getText().clear();
            className.getText().clear();
            emailAddress.getText().clear();
        });
    }
}