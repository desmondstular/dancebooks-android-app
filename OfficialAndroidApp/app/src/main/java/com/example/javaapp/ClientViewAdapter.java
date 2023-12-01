package com.example.javaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.javaapp.database_v2.ClientModel;

import java.util.List;

public class ClientViewAdapter extends ArrayAdapter<ClientModel> {

    public ClientViewAdapter(Context context, List<ClientModel> clientModelList) {
        super(context, 0, clientModelList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.client_view_item, parent, false);
        }

        // Get the current SignUpModel object
        ClientModel currentItem = getItem(position);

        // Set data to the views in the custom layout
        TextView clientName = listItemView.findViewById(R.id.clientNameTextView);
        TextView clientEmail = listItemView.findViewById(R.id.clientEmailTextView);
        TextView clientBalance = listItemView.findViewById(R.id.clientBalanceTextView);

        if (currentItem != null) {
            clientName.setText("Client Name: " + currentItem.getFirstName() + " " +currentItem.getLastName());
            clientEmail.setText("Client Email: " + currentItem.getEmail());
            clientBalance.setText("Client Balance: " + currentItem.getBalance() + "$");
        }

        return listItemView;
    }
}
