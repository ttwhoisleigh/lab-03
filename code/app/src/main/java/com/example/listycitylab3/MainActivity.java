package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener, EditCityFragment.EditCityDialogListener {

    private ArrayList<City> dataList; //creates array called dataList with each element being an instance of City
    private ListView cityList; //Creates a listview named cityList
    private ArrayAdapter<City> cityAdapter; //Makes an array adapter instance for City

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void editCity(City oldCity, City newCity) {
        dataList.remove(oldCity); // removing the old city from dataList and adding the new one below
        dataList.add(newCity);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //loads the UI defined in activity_main

        String[] cities = {"Edmonton", "Vancouver", "Toronto"}; //hardcoded values
        String[] provinces = {"AB", "BC", "ON"}; //hardcoded values

        dataList = new ArrayList<>();
        for (int i = 0; i< cities.length; ++i){
            dataList.add(new City(cities[i], provinces[i]));
        } //adds values into dataList

        cityList = findViewById(R.id.city_list); // Finds the list view in activity main and sets it to cityList
        cityAdapter = new CityArrayAdapter(this, dataList); //Creates instance of custom array adapter
        cityList.setAdapter(cityAdapter); //actually displays the cities cause cityList is the view, and we are setting the adapter to add the stuff in

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            new EditCityFragment(selectedCity).show(getSupportFragmentManager(), "Edit City");
        });

        FloatingActionButton fab = findViewById(R.id.button_add_city); //finds button in fragment add city
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        }); //creates new AddCityFragment, shows it on screen with fragment manager, and boom menu thing open
        }
}
