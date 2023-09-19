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
import com.mastercoding.unitsconverter.adapters.AreaAdapter;
import com.mastercoding.unitsconverter.adapters.VolumeAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentVolumeBinding;
import com.mastercoding.unitsconverter.models.AreaModelClass;
import com.mastercoding.unitsconverter.models.VolumeModelClass;

import java.util.ArrayList;

public class Volume extends Fragment {

    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    VolumeAdapter adapter;


    ArrayList<VolumeModelClass> volumeModel;

    private FragmentVolumeBinding fragmentVolumeBinding;

    double m3 = 1, l = 1000, cm3 = 1000000, ml = 1000000, mm3 = 1000000000, tblspn = 66666.66667, tspn = 200000, ft3 = 35.31466672, in3 = 61023.74409, yd3 = 1.307950619,
            usBarrel = 6.28981077, usGallon = 264.1720524, usDryGallon = 227.0207461, usFlOz = 33814.0227, usTblspn = 202884.1362, usTspn = 202884.1362,
            ukBarrel = 6.1103, ukGallon = 219.9692483, ukFlOz = 35195.07973;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentVolumeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_volume, container, false); // Has to be done instead of the view for the usage of databinding
        fragmentVolumeBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>(); // Filling an arraylist of strings that will be put in a spinner
        arrayListFrom.add("m^3");
        arrayListFrom.add("l");
        arrayListFrom.add("ml");
        arrayListFrom.add("cm^3");
        arrayListFrom.add("mm^3");
        arrayListFrom.add("yd^3");
        arrayListFrom.add("ft^3");
        arrayListFrom.add("in^3");
        arrayListFrom.add("tblspn");
        arrayListFrom.add("tspn");
        arrayListFrom.add("US Barrel");
        arrayListFrom.add("US Gallon");
        arrayListFrom.add("US Dry Gallon");
        arrayListFrom.add("US Fl Oz");
        arrayListFrom.add("US tblspn");
        arrayListFrom.add("US tspn");
        arrayListFrom.add("UK Barrel");
        arrayListFrom.add("UK Gallon");
        arrayListFrom.add("UK Fl Oz");

        spinnerFrom = (Spinner) fragmentVolumeBinding.volumeUnitsSpinner; // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), simple_spinner_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(simple_spinner_item);

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
        volumeModel.add(new VolumeModelClass("US Barrel\t", "6.28981077","US Barrel", usBarrel));
        volumeModel.add(new VolumeModelClass("US Gallon\t", "264.1720524","US Gallon", usGallon));
        volumeModel.add(new VolumeModelClass("US Dry Gallon\t", "227.0207461","US Dry Gallon", usDryGallon));
        volumeModel.add(new VolumeModelClass("US Fluid Ounce\t", "33814.0227","US Fl Oz", usFlOz));
        volumeModel.add(new VolumeModelClass("US Tablespoon\t", "202884.1362","US tblspn", usTblspn));
        volumeModel.add(new VolumeModelClass("US Teaspoon\t", "202884.1362","US tspn", usTspn));
        volumeModel.add(new VolumeModelClass("UK Barrel\t", "6.1103","UK Barrel", ukBarrel));
        volumeModel.add(new VolumeModelClass("UK Gallon\t", "219.9692483","UK Gallon", ukGallon));
        volumeModel.add(new VolumeModelClass("UK Fluid Ounce\t", "35195.07973","UK Fl Oz", ukFlOz));


        fragmentVolumeBinding.setLifecycleOwner(this);


        // Inflate the layout for this fragment
        return fragmentVolumeBinding.getRoot();
    }
}