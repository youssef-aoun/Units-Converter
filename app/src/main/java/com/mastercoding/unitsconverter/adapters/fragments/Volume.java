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
import com.mastercoding.unitsconverter.adapters.VolumeAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentVolumeBinding;
import com.mastercoding.unitsconverter.models.VolumeModelClass;

import java.util.ArrayList;
import java.util.Arrays;

public class Volume extends Fragment {

    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    VolumeAdapter adapter;


    ArrayList<VolumeModelClass> volumeModel;

    private FragmentVolumeBinding fragmentVolumeBinding;

    double m3 = 1, l = 1000, cm3 = 1000000, ml = 1000000, mm3 = 1000000000, tblspn = 66666.66667, tspn = 200000, ft3 = 35.31466672, in3 = 61023.74409, yd3 = 1.307950619;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentVolumeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_volume, container, false); // Has to be done instead of the view for the usage of data binding
        fragmentVolumeBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>();
        arrayListFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.volume)));

        spinnerFrom = (Spinner) fragmentVolumeBinding.volumeUnitsSpinner; // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(R.layout.dropdown_item);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new VolumeAdapter(volumeModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentVolumeBinding.volumeList.setHasFixedSize(true);
                fragmentVolumeBinding.volumeList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentVolumeBinding.volumeList.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        volumeModel = new ArrayList<>(); // Making the arraylist of units

        volumeModel.add(new VolumeModelClass("Cubic Meter\t", "1", "m^3",  m3));
        volumeModel.add(new VolumeModelClass("Liter\t", "1000","l",  l));
        volumeModel.add(new VolumeModelClass("Milliliter\t", "1000000", "ml", ml));
        volumeModel.add(new VolumeModelClass("Cubic Centimeter\t", "1000000","cm^3", cm3));
        volumeModel.add(new VolumeModelClass("Cubic Millimeter\t", "1000000000","mm^3", mm3));
        volumeModel.add(new VolumeModelClass("Cubic Yard\t", "1.307950619", "yd^3",yd3));
        volumeModel.add(new VolumeModelClass("Cubic Foot\t", "35.31466672","ft^3", ft3));
        volumeModel.add(new VolumeModelClass("Cubic Inch\t", "61023.74409","in^3", in3));
        volumeModel.add(new VolumeModelClass("Tablespoon (Metric)\t", "66666.66667", "tblspn", tblspn));
        volumeModel.add(new VolumeModelClass("Teaspoon (Metric)\t", "200000","tspn", tspn));


        fragmentVolumeBinding.setLifecycleOwner(this);


        // Inflate the layout for this fragment
        return fragmentVolumeBinding.getRoot();
    }
}