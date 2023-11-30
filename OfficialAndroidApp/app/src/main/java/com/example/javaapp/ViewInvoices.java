package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javaapp.database_v2.DatabaseDao;
import com.example.javaapp.database_v2.InvoiceModel;

import java.util.List;

public class ViewInvoices extends AppCompatActivity {
    LinearLayout goHomeClick, generateInvoiceClick;
    ListView invoiceList;
    DatabaseDao databaseDao;
    List<InvoiceModel> invoiceModelList;
    InvoiceAdapter invoiceArrayAdapter;
    Button searchForInvoiceBtn, payForInvoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invoices);

        //----------------navigation code-----------------------------------------------------------
        generateInvoiceClick = findViewById(R.id.createInvoiceClick);
        generateInvoiceClick.setOnClickListener(view -> {
            startActivity(new Intent(ViewInvoices.this, CreateInvoice.class));
        });
        goHomeClick = findViewById(R.id.goHomeClick);
        goHomeClick.setOnClickListener(view -> {
            startActivity(new Intent(ViewInvoices.this, HomePage.class));
        });
        //------------------------------------------------------------------------------------------
        //------------------------list view selection code------------------------------------------
        invoiceList = findViewById(R.id.invoiceList);
        databaseDao = new DatabaseDao(ViewInvoices.this);
        invoiceModelList = databaseDao.getAllInvoices();
        // Replace the ArrayAdapter declaration with your custom adapter
        invoiceArrayAdapter = new InvoiceAdapter(ViewInvoices.this, invoiceModelList);
        invoiceList.setAdapter(invoiceArrayAdapter);
        invoiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Handle item selection here
                invoiceArrayAdapter.setSelectedItem(i);
            }
        });
        //------------------------pay for selected invoice code-------------------------------------
        payForInvoiceBtn = findViewById(R.id.payForInvoiceBtn);
        payForInvoiceBtn.setOnClickListener(view1 -> {
            int selectedItemPosition = invoiceArrayAdapter.getSelectedItem();
            if (selectedItemPosition != -1) {
                InvoiceModel selectedItem = invoiceArrayAdapter.getItem(selectedItemPosition);
                try {
                    if (databaseDao.updateInvoiceToPaid(selectedItem.getInvoiceID())) {
                        Toast.makeText(ViewInvoices.this, "Paid invID: " + selectedItem.getInvoiceID(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewInvoices.this, "Invoice has already been paid", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ViewInvoices.this, "Cannot Pay for This Invoice", Toast.LENGTH_SHORT).show();
                }
                // Clear the selection
                invoiceArrayAdapter.setSelectedItem(-1);
                // Update the dataset and notify the adapter of the change
                invoiceModelList = databaseDao.getAllInvoices();
                invoiceArrayAdapter.clear();
                invoiceArrayAdapter.addAll(invoiceModelList);
                invoiceArrayAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(ViewInvoices.this, "Please select an invoice", Toast.LENGTH_SHORT).show();
            }
        });
        //------------------------------------------------------------------------------------------
        //------------------------search for invoice code-------------------------------------------
        searchForInvoiceBtn = findViewById(R.id.searchForInvoiceBtn);
        searchForInvoiceBtn.setOnClickListener(view -> {
            // Get the entered email from the EditText
            EditText searchEditText = findViewById(R.id.searchByClient);
            String searchEmail = searchEditText.getText().toString().trim().toUpperCase();

            // Perform the search and update the adapter
            if (!searchEmail.isEmpty()) {
                List<InvoiceModel> filteredInvoices = databaseDao.getAllInvoicesByClientEmail(searchEmail);
                invoiceArrayAdapter.clear();
                invoiceArrayAdapter.addAll(filteredInvoices);
                invoiceArrayAdapter.notifyDataSetChanged();
            } else {
                // If the search field is empty, show all invoices
                invoiceModelList = databaseDao.getAllInvoices();
                invoiceArrayAdapter.clear();
                invoiceArrayAdapter.addAll(invoiceModelList);
                invoiceArrayAdapter.notifyDataSetChanged();
            }

            // Clear the selection and text field
            invoiceArrayAdapter.setSelectedItem(-1);
            searchEditText.getText().clear();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }
}