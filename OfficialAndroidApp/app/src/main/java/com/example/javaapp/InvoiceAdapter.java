package com.example.javaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.javaapp.database_v2.InvoiceModel;

import java.util.List;

public class InvoiceAdapter extends ArrayAdapter<InvoiceModel> {
    private int selectedItem = -1;

    public InvoiceAdapter(Context context, List<InvoiceModel> invoiceModelList) {
        super(context, 0, invoiceModelList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.invoice_list_item, parent, false);
        }

        // Get the current InvoiceModel object
        InvoiceModel currentItem = getItem(position);

        // Set data to the views in the custom layout
        TextView invoiceIdTextView = listItemView.findViewById(R.id.invIDTextView);
        TextView invoiceEmailTextView = listItemView.findViewById(R.id.invEmailTextView);
        TextView totalCostTextView = listItemView.findViewById(R.id.totalCostTextView);
        TextView isPaidTextView = listItemView.findViewById(R.id.isPaidTextView);
        // Set the background color based on the selected item
        if (position == selectedItem) {
            listItemView.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_purple));
        } else {
            listItemView.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        }
        if (currentItem != null) {
            String invoiceIdText = "Invoice ID: " + String.valueOf(currentItem.getInvoiceID());
            invoiceEmailTextView.setText("Email: " + currentItem.getEmail());
            invoiceIdTextView.setText(invoiceIdText);
            totalCostTextView.setText("Total: " + currentItem.getTotalCost() + "$");
            isPaidTextView.setText("Paid: " + (currentItem.getIsPaid() == 1 ? "Yes" : "No"));
        }

        return listItemView;
    }


    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
