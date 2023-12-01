package com.example.javaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.javaapp.database_v2.ClassModel;

import java.util.List;

public class DanceClassAdapter extends ArrayAdapter<ClassModel> {

    public DanceClassAdapter(Context context, List<ClassModel> danceClassModelList) {
        super(context, 0, danceClassModelList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dance_class_view_item, parent, false);
        }

        // Get the current SignUpModel object
        ClassModel currentItem = getItem(position);

        // Set data to the views in the custom layout
        TextView className = listItemView.findViewById(R.id.classNameTextView);
        TextView classYear = listItemView.findViewById(R.id.classYearTextView);
        TextView classCost = listItemView.findViewById(R.id.classCostTextView);
        TextView classEnroll = listItemView.findViewById(R.id.classEnrollTextView);

        if (currentItem != null) {
            className.setText("Class Name: " + currentItem.getClassName());
            classYear.setText("Class Year: " + currentItem.getYear());
            classCost.setText("Class Cost: " + currentItem.getCost() + "$");
            classEnroll.setText("Class Enrollment: " + currentItem.getEnrolled() + "/" + currentItem.getCapacity());
        }

        return listItemView;
    }
}
