package com.example.listycity;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    public CityArrayAdapter(Context context, ArrayList<City> cities)
    {
        super(context, 0, cities);
    }
}
