package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javaapp.database_v2.ClassModel;
import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.helpers.Clean;

import java.util.List;

public class DanceClassView extends AppCompatActivity {
    //references to buttons and other controls on the layout
    Button searchBtn, availableClassesBtn;
    EditText className, classYear;
    ListView lv_danceClassList;
    DatabaseDao databaseDao;
    ArrayAdapter<ClassModel> classModelArrayAdapter;
    List<ClassModel> classModelList;
    LinearLayout addClassClick, goHomeClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_class_view);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("View Dance Classes");
        lv_danceClassList = findViewById(R.id.lv_danceClassList);
        //------------------------------------------------------------------------------------------
        //Navigation Bar Start**********************************************************************
        addClassClick = findViewById(R.id.addClassClick);
        addClassClick.setOnClickListener(view -> {
            startActivity(new Intent(DanceClassView.this, AddClass.class));
        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(DanceClassView.this, HomePage.class));
        });
        //Navigation Bar End************************************************************************
        //------------------------------------------------------------------------------------------
        //DATA BASE VIEW
        databaseDao = new DatabaseDao(DanceClassView.this);
        classModelList = databaseDao.getAllClasses();
        classModelArrayAdapter = new DanceClassAdapter(DanceClassView.this, classModelList);
        lv_danceClassList.setAdapter(classModelArrayAdapter);
        //---------------init the fields for search----------------------------------
        searchBtn = findViewById(R.id.searchClassBtn);
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
                classModelList = databaseDao.getAllClassesByClassNameAndOrYear(className.getText().toString().toUpperCase(), null);
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
                classModelList = databaseDao.getAllClassesByClassNameAndOrYear("", Integer.parseInt(classYear.getText().toString()));
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
                        className.getText().toString().toUpperCase(),classYearInt));
            }
            classModelArrayAdapter = new DanceClassAdapter(DanceClassView.this, classModelList);
            if (classModelList.isEmpty()){
                Toast.makeText(DanceClassView.this, "No Classes found",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                lv_danceClassList.setAdapter(classModelArrayAdapter);
            }
            // hide keyboard on click
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            classYear.getText().clear();
            className.getText().clear();
        });
        //-------------on click for show all available btn------------------------------------------
        availableClassesBtn = findViewById(R.id.availableClassesBtn);
        availableClassesBtn.setOnClickListener(view -> {
            try
            {
                classModelList.clear();
                classModelList = databaseDao.getAllAvailableClasses();
                classModelArrayAdapter = new DanceClassAdapter(DanceClassView.this, classModelList);
                lv_danceClassList.setAdapter(classModelArrayAdapter);
            }
            catch (Exception e)
            {
                Toast.makeText(DanceClassView.this, "None found",
                        Toast.LENGTH_SHORT).show();
            }
            classYear.getText().clear();
            className.getText().clear();
        });
    }
}