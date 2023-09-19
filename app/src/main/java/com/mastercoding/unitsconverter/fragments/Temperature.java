package com.mastercoding.unitsconverter.fragments;

import static android.R.layout.simple_spinner_item;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.adapters.TemperatureAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentTemperatureBinding;
import com.mastercoding.unitsconverter.models.TemperatureModelClass;

import java.util.ArrayList;

public class Temperature extends Fragment {

    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    TemperatureAdapter adapter;

    ArrayList<TemperatureModelClass> temperatureModel;

    private FragmentTemperatureBinding fragmentTemperatureBinding;

    double celsius = 0, fahrenheit = 32, kelvin = 273.15, rankine = 491.67;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentTemperatureBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_temperature, container, false); // Has to be done instead of the view for the usage of databinding
        fragmentTemperatureBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>(); // Filling an arraylist of strings that will be put in a spinner

        arrayListFrom.add("celsius");
        arrayListFrom.add("fahrenheit");
        arrayListFrom.add("kelvin");
        arrayListFrom.add("rankine");

        spinnerFrom = (Spinner) fragmentTemperatureBinding.temperatureUnitsSpinner; // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), simple_spinner_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(simple_spinner_item);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new TemperatureAdapter(temperatureModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentTemperatureBinding.temperatureList.setHasFixedSize(true);
                fragmentTemperatureBinding.temperatureList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentTemperatureBinding.temperatureList.setAdapter(adapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        temperatureModel = new ArrayList<>();  // Making the arraylist of units

        temperatureModel.add(new TemperatureModelClass("Celsius\t", "°C", "0",  celsius));
        temperatureModel.add(new TemperatureModelClass("Fahrenheit\t", "°F", "32",  fahrenheit));
        temperatureModel.add(new TemperatureModelClass("Kelvin\t", "K", "273.15",  kelvin));
        temperatureModel.add(new TemperatureModelClass("Rankine\t", "°Ra", "491.67",  rankine));

        fragmentTemperatureBinding.setLifecycleOwner(this);

        // Inflate the layout for this fragment
        return fragmentTemperatureBinding.getRoot();
    }
}