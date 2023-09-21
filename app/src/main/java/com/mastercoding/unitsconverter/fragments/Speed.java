package com.mastercoding.unitsconverter.fragments;

import static android.R.layout.simple_spinner_item;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.adapters.SpeedAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentSpeedBinding;
import com.mastercoding.unitsconverter.models.SpeedModelClass;

import java.util.ArrayList;
import java.util.Arrays;

public class Speed extends Fragment {

    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    SpeedAdapter adapter;


    ArrayList<SpeedModelClass> speedModel;


    private FragmentSpeedBinding fragmentSpeedBinding;

    double ms = 1, kmh = 3.6, mileh = 2.2369363, knot = 1.9438445, cmh = 360000, cmmin = 6000, cms = 100, fth = 11811.02362, ftmin = 196.8503937,
            fts = 3.280834, inh = 141732.2835, inmin = 2362.204724, ins = 39.37007874, mh = 3600, mmin = 60, mms = 1000;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSpeedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_speed, container, false); // Has to be done instead of the view for the usage of data binding
        fragmentSpeedBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>();
        arrayListFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.speed)));

        spinnerFrom = (Spinner) fragmentSpeedBinding.speedUnitsSpinner; // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(R.layout.dropdown_item);


        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new SpeedAdapter(speedModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentSpeedBinding.speedList.setHasFixedSize(true);
                fragmentSpeedBinding.speedList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentSpeedBinding.speedList.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        speedModel = new ArrayList<>(); // Making the arraylist of units

        speedModel.add(new SpeedModelClass("Kilometers per Hour\t", "km/h","3.6",  kmh));
        speedModel.add(new SpeedModelClass("Mile per Hour\t", "mile/h", "2.2369363", mileh));
        speedModel.add(new SpeedModelClass("Know\t", "knot","1.9438445", knot));
        speedModel.add(new SpeedModelClass("Centimeter per Hour\t", "cm/h","360000", cmh));
        speedModel.add(new SpeedModelClass("Centimeter per Minute\t", "cm/min", "6000",cmmin));
        speedModel.add(new SpeedModelClass("Centimeter per Second\t", "cm/s","100", cms));
        speedModel.add(new SpeedModelClass("Foot per Hour\t", "ft/h","11811.02362", fth));
        speedModel.add(new SpeedModelClass("Foot per Minute\t", "ft/min", "196.8503937", ftmin));
        speedModel.add(new SpeedModelClass("Foot per Second\t", "ft/s","3.280834", fts));
        speedModel.add(new SpeedModelClass("Inch per Hour\t", "in/h","141732.2835", inh));
        speedModel.add(new SpeedModelClass("Inch per Minute\t", "in/min","2362.204724", inmin));
        speedModel.add(new SpeedModelClass("Inch per Second\t", "in/s","39.37007874", ins));
        speedModel.add(new SpeedModelClass("Meter per Hour\t", "m/h","3600", mh));
        speedModel.add(new SpeedModelClass("Meter per Minute\t", "m/min","60", mmin));
        speedModel.add(new SpeedModelClass("Meter per Second\t", "m/s", "1",  ms));
        speedModel.add(new SpeedModelClass("Millimeter per Second\t", "mm/s","1000", mms));



        fragmentSpeedBinding.setLifecycleOwner(this);

        // Inflate the layout for this fragment
        return fragmentSpeedBinding.getRoot();
    }
}