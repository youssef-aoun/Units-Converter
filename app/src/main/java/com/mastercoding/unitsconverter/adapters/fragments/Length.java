package com.mastercoding.unitsconverter.adapters.fragments;

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
import com.mastercoding.unitsconverter.adapters.LengthAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentLengthBinding;
import com.mastercoding.unitsconverter.models.LengthModelClass;

import java.util.ArrayList;
import java.util.Arrays;

public class Length extends Fragment {


    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    LengthAdapter adapter;


    ArrayList<LengthModelClass> lengthModel;


    private FragmentLengthBinding fragmentLengthBinding;


    double  km = 0.001, m = 1, cm = 100, mm = 1000, yd = 1.093613298, ft = 3.280839895, in = 39.37007874, mile = 0.00062137,
            nmi = 0.000539956, decimeter = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentLengthBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_length, container, false); // Has to be done instead of the view for the usage of data binding
        fragmentLengthBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>(); // Filling an arraylist of strings that will be put in a spinner

        arrayListFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.length)));

        spinnerFrom = (Spinner) fragmentLengthBinding.lengthUnitsSpinner; // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(R.layout.dropdown_item);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new LengthAdapter(lengthModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentLengthBinding.lengthList.setHasFixedSize(true);
                fragmentLengthBinding.lengthList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentLengthBinding.lengthList.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lengthModel = new ArrayList<>(); // Making the arraylist of units

       lengthModel.add(new LengthModelClass("Meter\t", "m", "1",  m));
       lengthModel.add(new LengthModelClass("Millimeter\t", "mm","1000",  mm));
       lengthModel.add(new LengthModelClass("Centimeter\t", "cm", "100", cm));
       lengthModel.add(new LengthModelClass("Kilometers\t", "km","0.001", km));
       lengthModel.add(new LengthModelClass("Mile\t", "mile","0.00062137", mile));
       lengthModel.add(new LengthModelClass("Foot\t", "ft", "3.280839895",ft));
       lengthModel.add(new LengthModelClass("Inch\t", "in","39.37007874", in));
       lengthModel.add(new LengthModelClass("Yard\t", "yd","1.093613298", yd));
       lengthModel.add(new LengthModelClass("Acre\t", "nmi", "0.000539956", nmi));
       lengthModel.add(new LengthModelClass("Decimeter\t", "decimeter","10", decimeter));


       fragmentLengthBinding.setLifecycleOwner(this);

        // Inflate the layout for this fragment
        return fragmentLengthBinding.getRoot();
    }
}