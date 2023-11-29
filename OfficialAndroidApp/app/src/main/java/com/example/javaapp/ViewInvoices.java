package com.example.javaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    ArrayAdapter<InvoiceModel> invoiceArrayAdapter;
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
        invoiceList = findViewById(R.id.invoiceList);
        databaseDao = new DatabaseDao(ViewInvoices.this);
        invoiceModelList = databaseDao.getAllInvoices();
        invoiceArrayAdapter = new ArrayAdapter<InvoiceModel>(ViewInvoices.this,
                android.R.layout.simple_list_item_multiple_choice, invoiceModelList);
        invoiceList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        invoiceList.setAdapter(invoiceArrayAdapter);
        invoiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    InvoiceModel item = (InvoiceModel) adapterView.getItemAtPosition(i);
                    payForInvoiceBtn = findViewById(R.id.payForInvoiceBtn);
                    payForInvoiceBtn.setOnClickListener(view1 -> {
                        try {
                            databaseDao.updateInvoiceToPaid(item.getInvoiceID());
                            Toast.makeText(ViewInvoices.this, "selected: " + item, Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(ViewInvoices.this, "Cannot Pay for This Invoice", Toast.LENGTH_SHORT).show();
                        }
                        databaseDao.updateInvoiceToPaid(item.getInvoiceID());
                        invoiceModelList = databaseDao.getAllInvoices();
                        invoiceArrayAdapter = new ArrayAdapter<InvoiceModel>(ViewInvoices.this,
                                android.R.layout.simple_list_item_multiple_choice, invoiceModelList);
                        invoiceList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        invoiceList.setAdapter(invoiceArrayAdapter);

                    });
                }
        });
        //------------------------------------------------------------------------------------------

    }
}