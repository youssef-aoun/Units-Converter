package com.mastercoding.unitsconverter.fragments;

import static android.R.layout.simple_spinner_item;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.adapters.TimeAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentTimeBinding;
import com.mastercoding.unitsconverter.models.TimeModelClass;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Time extends Fragment {

    Spinner spinnerFrom;

    ArrayList<TimeModelClass> timeModel;

    TimeAdapter adapter;
    private FragmentTimeBinding fragmentTimeBinding;
    String selectedSpinnerItem = "";

    double minute = 60, hour = 1, second = 3600, week = 0.005952381, year = 0.00011408, century = 0.00000114079552, decade = 0.0000114079552,
            millisecond = 3000000, microsecond = 360000000*10;

    double day = hour/24;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentTimeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_time, container, false); // Has to be done instead of the view for the usage of databinding
        fragmentTimeBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>(); // Filling an arraylist of strings that will be put in a spinner

        arrayListFrom.add("day");
        arrayListFrom.add("minute");
        arrayListFrom.add("hour");
        arrayListFrom.add("second");
        arrayListFrom.add("week");
        arrayListFrom.add("year");
        arrayListFrom.add("century");
        arrayListFrom.add("decade");
        arrayListFrom.add("millisecond");
        arrayListFrom.add("microsecond");

        spinnerFrom = (Spinner) fragmentTimeBinding.timeUnitsSpinner;  // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), simple_spinner_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(simple_spinner_item);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new TimeAdapter(timeModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentTimeBinding.timeList.setHasFixedSize(true);
                fragmentTimeBinding.timeList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentTimeBinding.timeList.setAdapter(adapter);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timeModel = new ArrayList<>(); // Making the arraylist of units

        timeModel.add(new TimeModelClass("Day\t", "day", "0.041666667", day));
        timeModel.add(new TimeModelClass("Minute\t", "min", "60", minute));
        timeModel.add(new TimeModelClass("hour\t", "hour", "1", hour));
        timeModel.add(new TimeModelClass("Second\t", "sec", "3600", second));
        timeModel.add(new TimeModelClass("Week\t", "week", "0.005952381", week));
        timeModel.add(new TimeModelClass("Year\t", "year", "0.00011408", year));
        timeModel.add(new TimeModelClass("Century\t", "century", "0.00000114079552", century));
        timeModel.add(new TimeModelClass("Decade\t", "decade", "0.0000114079552", decade));
        timeModel.add(new TimeModelClass("Millisecond\t", "millisecond", "3000000", millisecond));
        timeModel.add(new TimeModelClass("Microsecond\t", "microsecond", "3600000000", microsecond));

        fragmentTimeBinding.setLifecycleOwner(this);
        // Inflate the layout for this fragment
        return fragmentTimeBinding.getRoot();
    }
}