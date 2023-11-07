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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.database_v2.SignedUpModel;

import java.util.List;

public class SignUpView extends AppCompatActivity {
    Button addClientsBtn, viewClientsBtn, addInvoiceBtn, addClassBtn, viewClassBtn,
    searchSignupsButton;
    Switch isPaidSwitch;
    ListView lv_SignedUp_List;
    EditText emailAddress, className, classYear;
    DatabaseDao databaseDao;
    ArrayAdapter<SignedUpModel> signedUpModelArrayAdapter;
    List<SignedUpModel> signedUpModelList;

    RadioButton both, paid, unPaid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);
        lv_SignedUp_List = findViewById(R.id.lv_Invoice_list);
        //Navigation Bar Start *********************************************************************
        // Set click listener for Add Classes button
        addClassBtn = findViewById(R.id.addClassBtn);
        addClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, AddClass.class));
        });
        addInvoiceBtn = findViewById(R.id.addInvoiceBtn);
        addInvoiceBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, ClassSignUp.class));
        });
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, AddClient.class));
        });
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, ClientView.class));
        });
        viewClassBtn = findViewById(R.id.viewClassBtn);
        viewClassBtn.setOnClickListener(view -> {
            startActivity(new Intent(SignUpView.this, DanceClassView.class));
        });
        //Navigation Bar End ***********************************************************************
        //DataBase View-----------------------------------------------------------------------------
        databaseDao = new DatabaseDao(SignUpView.this);
        signedUpModelList = databaseDao.getAllSignedUps();
        signedUpModelArrayAdapter = new ArrayAdapter<>(SignUpView.this,
                android.R.layout.simple_list_item_1, signedUpModelList);
        lv_SignedUp_List.setAdapter(signedUpModelArrayAdapter);
        //--------------------init the fields for search--------------------------------------------
        searchSignupsButton = findViewById(R.id.search_sign_ups);
        //isPaidSwitch = findViewById(R.id.is_paid_switch);
        emailAddress = findViewById(R.id.search_client_email_sign_up);
        className = findViewById(R.id.search_class_name);
        classYear = findViewById(R.id.search_class_year);
        both   = findViewById(R.id.bothRbtn);
        paid   = findViewById(R.id.paidRbtn);
        unPaid = findViewById(R.id.unPaidRbtn);
        both.setChecked(true);
        //------------------------------------------------------------------------------------------
        //Changes to View Based on Search Criteria
        searchSignupsButton.setOnClickListener(view -> {
            signedUpModelList.clear();
            int classYearInt, isPaid;
            if (both.isChecked()){
                isPaid = -1;
            } else if (paid.isChecked()) {
                isPaid = 1;
            }
            else
                isPaid = 0;
            if (classYear.getText().toString().isEmpty()){
                classYearInt = 0;
            }
            else{
                classYearInt = Integer.parseInt(classYear.getText().toString());
            }
            signedUpModelList = databaseDao.getFromTextFields(
                    emailAddress.getText().toString(),
                    className.getText().toString(),
                    classYearInt,
                    isPaid);
            signedUpModelArrayAdapter = new ArrayAdapter<>(SignUpView.this,
                    android.R.layout.simple_list_item_1, signedUpModelList);
            lv_SignedUp_List.setAdapter(signedUpModelArrayAdapter);
            emailAddress.getText().clear();
            classYear.getText().clear();
            className.getText().clear();
            // hide keyboard on click
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            if (signedUpModelList.isEmpty()){
                Toast.makeText(SignUpView.this, "No Signups found",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}