package com.example.lgw6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListData> {
    private ArrayList<ListData> data;
    private Context context;

    CustomAdapter(Context context, ArrayList<ListData> data) {
        super(context, R.layout.list_item, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, null);
        }

        ImageView image = rowView.findViewById(R.id.left_pic);
        TextView country = rowView.findViewById(R.id.tv_listitem);


        // Get individual object from  ArrayList<ListData> and set ListView items
        ListData temp_data = data.get(position);
        image.setImageResource(temp_data.getImage());
        country.setText(temp_data.getCountry());


        return rowView;
    }
}
