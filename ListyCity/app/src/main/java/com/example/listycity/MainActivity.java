package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static MainActivity Instance;
    private ListView cityList;
    private City selectedCity;
    private LinearLayout promptArea;
    private EditText inputArea;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<City> cities;
    private ArrayList<String> namesList;

    public static MainActivity getInstance() {
        return Instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Instance = this;

        promptArea = findViewById(R.id.prompt_name);
        showPrompt(false);

        cityList = findViewById(R.id.city_list);

        //Initial cities
        addCity("Edmonton");
        addCity("Vancouver");
        addCity("Moscow");
        //addCity("Sydney");
        //addCity("Berlin");
        //addCity("Vienna");
        //addCity("Tokyo");
        //addCity("Beijing");
        //addCity("Osaka");
        //addCity("New Delhi");

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrompt(true);
            }
        });

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCity != null && cities.contains(selectedCity))
                {
                    cities.remove(selectedCity);
                    namesList.remove(selectedCity.getName());
                    updateCityList();
                }
            }
        });
        Button confirmButton = findViewById(R.id.confirm_button);
        inputArea = findViewById(R.id.input_area);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCity(inputArea.getText().toString());
                showPrompt(false);
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                selectedCity = cities.get((int)id);
            }
        });
    }

    private void showPrompt(boolean show)
    {
        if (show) {
            inputArea.setText("");
            promptArea.setVisibility(View.VISIBLE);
        }
        else {
            promptArea.setVisibility(View.GONE);
        }
    }

    private void updateCityList()
    {
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, namesList);
        cityList.setAdapter(cityAdapter);
    }
    public void addCity(String cityName)
    {
        if (cities == null) {
            cities = new ArrayList<City>();
        }

        if (namesList == null) {
            namesList = new ArrayList<>();
        }
        City city = new City(cityName, "Placeholder");
        cities.add(city);
        namesList.add(cityName);
        updateCityList();
    }
}