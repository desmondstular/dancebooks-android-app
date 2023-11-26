package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class HomePage extends AppCompatActivity {
    LinearLayout addClientClick, viewClientClick, addClassClick,
            viewClassClick, addSignUpClick, viewSignUpClick,
            createInvoiceClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //----------init navigation handlers--------------------------------------------------------
        addClientClick = findViewById(R.id.addClientClick);
        viewClientClick = findViewById(R.id.viewClientClick);
        addClassClick = findViewById(R.id.addClassClick);
        viewClassClick = findViewById(R.id.viewClassesClick);
        addSignUpClick = findViewById(R.id.signUpClientClick);
        viewSignUpClick = findViewById(R.id.viewSignUpsClick);
        createInvoiceClick = findViewById(R.id.createInvoiceClick);
        //----------------navigation code-----------------------------------------------------------
        addClientClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, AddClient.class));
        });
        viewClientClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, ClientView.class));
        });
        addClassClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, AddClass.class));
        });
        viewClassClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, DanceClassView.class));
        });
        addSignUpClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, ClassSignUp.class));
        });
        viewSignUpClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, SignUpView.class));
        });
        createInvoiceClick.setOnClickListener(view -> {
            startActivity(new Intent(HomePage.this, CreateInvoice.class));
        });
    }
}