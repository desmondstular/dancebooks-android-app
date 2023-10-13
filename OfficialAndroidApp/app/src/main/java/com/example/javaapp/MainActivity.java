package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //references to buttons and other controls on the layout
    Button btnAddClass, addClientsBtn, viewClientsBtn;
    EditText ClName, ClYear, An_Price, Bi_Price, Mn_Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------------------------------
        // Set click listener for Add Clients button
        addClientsBtn = findViewById(R.id.addClientsBtn);
        addClientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to MainActivity
                Intent intent = new Intent(MainActivity.this, AddClient.class);
                startActivity(intent);
                finish(); // Optional: Close this activity if you don't want to keep it in the back stack
            }
        });

        // Set click listener for View Clients button
        viewClientsBtn = findViewById(R.id.viewClientsBtn);
        viewClientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the ClientView activity
                Intent intent = new Intent(MainActivity.this, ClientView.class);
                startActivity(intent);
            }
        });
        //------------------------------------------------------------------------------------------

        btnAddClass = findViewById(R.id.btnAddClass);
        ClName = findViewById(R.id.Cl_Name);
        ClYear = findViewById(R.id.Cl_Year);
        An_Price = findViewById(R.id.An_Price);
        Bi_Price = findViewById(R.id.BI_Price);
        Mn_Price = findViewById(R.id.Mn_Price);

        //btn listeners
        //btn_Add_Class.set

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v
             * Purpose:
             * Gets the input from the user OnClick of "AddClass" btn
             * and creates the Dance Class Object, then displays the toString() of the Dance Class
             *
             */
            @Override
            public void onClick(View v) {
                try {
                    DanceClassModel danceClassModel = new DanceClassModel(-1, ClName.getText().toString(),
                            Integer.parseInt(ClYear.getText().toString()),
                            Integer.parseInt(An_Price.getText().toString()),
                            Integer.parseInt(Bi_Price.getText().toString()),
                            Integer.parseInt(Mn_Price.getText().toString()));
                    Toast.makeText(MainActivity.this, danceClassModel.toString(),
                            Toast.LENGTH_SHORT).show();
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