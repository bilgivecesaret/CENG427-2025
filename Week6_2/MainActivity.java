package com.example.week6_2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends ListActivity {

    String[] cities = { "Istanbul", "Ankara", "Izmir", "Antalya",
            "Konya", "Izmit", "Bursa", "Kayseri", "Malatya", "Trabzon",
            "Antakya", "Erzurum", "Van" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //---no need to call this---
        //setContentView(R.layout.activity_main);

        ListView lstView = getListView();
        lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        //lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstView.setTextFilterEnabled(true);

        //cities = getResources().getStringArray(R.array.cities_array);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, cities));
    }

    public void onListItemClick(
            ListView parent, View v, int position, long id)
    {
        Toast.makeText(this,
                "You have selected " + cities[position],
                Toast.LENGTH_SHORT).show();
    }
}