package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//DialogFragment is just a popup dialog basically
public class AddCityFragment extends DialogFragment {
   //interfaces are a list of functions

    interface AddCityDialogListener { // interface declaration, whoever uses this must implement what is inside
        void addCity (City city);
    }
    private AddCityDialogListener listener; // will hold reference to AddCityDialogListener, aka the callback mechanism
    // example implementation listener.addCity(blank);

    @Override
    public void onAttach(@NonNull Context context) { // When the fragment is first attached to the parent
        super.onAttach(context); // needed not to break the code, context is a reference to the activity

        if (context instanceof AddCityDialogListener){
            listener = (AddCityDialogListener) context; // saved in listener, now can be called with listener.etc
        }
        else{ // prevents silent failures
            throw new RuntimeException(context + "must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        // inflates, aka takes the xml layout and turns it into a view, contains the two edit text fields

        EditText editCityName = view.findViewById(R.id.edit_text_city_text); // makes the edit text things, the view part is becasue this is found wihtin a view, not the activity itself
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // builds the fragment
        return builder
                .setView(view) // sets the view to the fragmentaddcity thing, defined up top with View view
                .setTitle("Add a City") // title
                .setNegativeButton("Cancel", null) // closes window
                .setPositiveButton("Add", (dialog, which) -> {
                    // reads the text from the edittext fields and then puts it to string and signs it to the variable
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    listener.addCity(new City(cityName, provinceName));
                    // the above passes in the values we just stored into the listener so we can call addCity
                })
                .create(); // builds the alert dialogue object
    }
}
