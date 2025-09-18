package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    public CityArrayAdapter(Context context, ArrayList<City> cities){
        super(context, 0, cities); //calls parent constructor
        //cities is list of City objects we made in MainActivity.java
        //0 means we are not using built in layout but making it later
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        // this method is called by ListView whenever it needs to display a row
        // position = item in the list currently displaying
        // this method returns view aka the row in the list
        // convertView uses previous instances that aren't displayed on screen and reuses them for new rows, only displays 10ish rows at a time instead of like 100 or something
        View view;
        if (convertView == null) { // aka if no recycled row exists, create a new one using content.xml
            view = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        }
        else{
            view = convertView; // if there is a view, this will recycle it to the new value
        }

        City city = getItem(position); // grabs the city object for specific row
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);
        // the above finds the two text views and assigns them to variables

        cityName.setText(city.getName()); // sets the city name in the row to the item
        provinceName.setText(city.getProvince()); // sets the province name in the row, city.getName() gets current name of item and then it gets set properly

        return view; // returns updated view
    }


}
