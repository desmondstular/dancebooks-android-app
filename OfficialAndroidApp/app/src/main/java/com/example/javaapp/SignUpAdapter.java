package com.example.javaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.javaapp.database_v2.SignedUpModel;

import java.util.List;

public class SignUpAdapter extends ArrayAdapter<SignedUpModel> {

    public SignUpAdapter(Context context, List<SignedUpModel> signedUpModelList) {
        super(context, 0, signedUpModelList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.sign_up_view_item, parent, false);
        }

        // Get the current SignUpModel object
        SignedUpModel currentItem = getItem(position);

        // Set data to the views in the custom layout
        TextView emailTextView = listItemView.findViewById(R.id.signUpEmailTextView);
        TextView className = listItemView.findViewById(R.id.classNameTextView);
        TextView classYear = listItemView.findViewById(R.id.classYearTextView);
        TextView invId = listItemView.findViewById(R.id.signUpInvID);

        if (currentItem != null) {
            emailTextView.setText(currentItem.getEmail());
            className.setText("Class Name: " + currentItem.getClassName());
            classYear.setText("Class Year: " + currentItem.getYear());
            if (currentItem.getInvoiceID() == 0) {
                invId.setText("Not Invoiced");
            }
            else {
                String idNum = String.valueOf(currentItem.getInvoiceID());
                invId.setText("Invoice ID: " + idNum);
            }
        }

        return listItemView;
    }
}
