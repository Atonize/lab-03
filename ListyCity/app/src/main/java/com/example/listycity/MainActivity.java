package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {
    private ListView cityListView;
    private CityArrayAdapter cityAdapter;
    private ArrayList<City> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++)
        {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityListView = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityListView.setAdapter(cityAdapter);

        FloatingActionButton addButton = findViewById(R.id.button_add_city);
        addButton.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            AddCityFragment fragment = new AddCityFragment(selectedCity);
            fragment.show(getSupportFragmentManager(), "Add City");
        });
    }

    public void addCity(City city)
    {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    public void editCity(City city, String name, String province)
    {
        city.setName(name);
        city.setProvince(province);
        cityAdapter.notifyDataSetChanged();
    }
}