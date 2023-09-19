package com.mastercoding.unitsconverter.fragments;

import static android.R.layout.simple_spinner_item;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.adapters.AreaAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentAreaBinding;
import com.mastercoding.unitsconverter.models.AreaModelClass;

import java.util.ArrayList;

public class Area extends Fragment{

    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    AreaAdapter adapter;


    ArrayList<AreaModelClass> areaModel;

    private FragmentAreaBinding fragmentAreaBinding;


    double km2 = 0.000001, acre = 0.000247105, ha = 0.0001, m2 = 1, cm2 = 10000, mm2 = 1000000, yd2 = 1.196, ft2 = 10.7639, in2 = 1550, mile2 = 0.000000386102;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentAreaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_area, container, false); // Has to be done instead of the view for the usage of databinding
        fragmentAreaBinding.getRoot().clearFocus();


        ArrayList<String> arrayListFrom = new ArrayList<>(); // Filling an arraylist of strings that will be put in a spinner
        arrayListFrom.add("m^2");
        arrayListFrom.add("cm^2");
        arrayListFrom.add("mm^2");
        arrayListFrom.add("km^2");
        arrayListFrom.add("mile^2");
        arrayListFrom.add("ft^2");
        arrayListFrom.add("in^2");
        arrayListFrom.add("yd^2");
        arrayListFrom.add("acre");
        arrayListFrom.add("ha");

        spinnerFrom = (Spinner) fragmentAreaBinding.areaUnitsSpinner; // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), simple_spinner_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(simple_spinner_item);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new AreaAdapter(areaModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentAreaBinding.areaList.setHasFixedSize(true);
                fragmentAreaBinding.areaList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentAreaBinding.areaList.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        areaModel = new ArrayList<>(); // Making the arraylist of units

        areaModel.add(new AreaModelClass("Squared Meter\t", "m^2", "1",  m2));
        areaModel.add(new AreaModelClass("Squared Millimeter\t", "mm^2","1000000",  mm2));
        areaModel.add(new AreaModelClass("Squared Centimeter\t", "cm^2", "10000", cm2));
        areaModel.add(new AreaModelClass("Squared Kilometers\t", "km^2","0.000001", km2));
        areaModel.add(new AreaModelClass("Squared Mile\t", "mile^2","0.000000386102", mile2));
        areaModel.add(new AreaModelClass("Squared Foot\t", "ft^2", "10.7639",ft2));
        areaModel.add(new AreaModelClass("Squared Inch\t", "in^2","1550", in2));
        areaModel.add(new AreaModelClass("Squared Yard\t", "yd^2","1.196", yd2));
        areaModel.add(new AreaModelClass("Acre\t", "acre", "0.000247105", acre));
        areaModel.add(new AreaModelClass("Hectare\t", "ha","0.0001", ha));



        fragmentAreaBinding.setLifecycleOwner(this);




        // Inflate the layout for this fragment
        return fragmentAreaBinding.getRoot();
    }
}