package com.example.javaapp;

import static com.example.javaapp.helpers.Clean.cleanIntInput;
import static com.example.javaapp.helpers.Clean.cleanName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;

public class AddClass extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button addClassBtn;
    LinearLayout viewClassClick, goHomeClick;
    EditText ClName, ClYear, An_Price, classCapacity;
    DatabaseDao databaseDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start *********************************************************************
        // Set click listener for Add Clients button, and all other screens
        viewClassClick = findViewById(R.id.viewClassClick);
        viewClassClick.setOnClickListener(view -> {
            startActivity(new Intent(AddClass.this, DanceClassView.class));
        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(AddClass.this, HomePage.class));
        });
        //Navigation Bar End ***********************************************************************
        //------------------------------------------------------------------------------------------

        //-----------------------ADD CLASS BTN FUNCTIONALITY----------------------------------------
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
                if (ClName.getText().toString().isEmpty() ||
                        ClYear.getText().toString().isEmpty() ||
                        An_Price.getText().toString().isEmpty() ||
                        classCapacity.getText().toString().isEmpty())
                {
                    Toast.makeText(AddClass.this, "PLease Fill Out All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        databaseDao = new DatabaseDao(AddClass.this);
                        ClassModel classModel = new ClassModel(
                                cleanName(ClName.getText().toString().toUpperCase()),
                                Integer.parseInt(cleanIntInput(ClYear.getText().toString())),
                                Integer.parseInt(cleanIntInput(An_Price.getText().toString())),
                                Integer.parseInt(cleanIntInput(classCapacity.getText().toString())), 0);
                        boolean i = databaseDao.addOneClass(classModel);
                        if (!i) {
                            Toast.makeText(AddClass.this, "Class Already Exists", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddClass.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            ClName.getText().clear();
                            ClYear.getText().clear();
                            An_Price.getText().clear();
                            classCapacity.getText().clear();
                        }
                    } catch (Exception e) {
                        Toast.makeText(AddClass.this, "Error Creating Dance Class",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //------------------------------------------------------------------------------------------
    }
}