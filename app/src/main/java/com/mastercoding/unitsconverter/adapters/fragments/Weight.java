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
import com.mastercoding.unitsconverter.adapters.WeightAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentWeightBinding;
import com.mastercoding.unitsconverter.models.WeightModelClass;

import java.util.ArrayList;
import java.util.Arrays;

public class Weight extends Fragment {


    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    WeightAdapter adapter;
    ArrayList<WeightModelClass> weightModel;


    private FragmentWeightBinding fragmentWeightBinding;

    double g = 1000, kg = 1, lb = 2.2046226, oz = 35.273962, carat = 5000, dram = 564.3833912, mg = 1000000, dwt = 643.0147943, grain = 15432.35835,
            ton = 0.001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentWeightBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weight, container, false); // Has to be done instead of the view for the usage of data binding
        fragmentWeightBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>();
        arrayListFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.weight)));

        spinnerFrom = (Spinner) fragmentWeightBinding.weightUnitsSpinner;  // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(R.layout.dropdown_item);


        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Getting the item selected in the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinnerItem = arrayListFrom.get(position);
                adapter = new WeightAdapter(weightModel, getContext(), spinnerFrom.getSelectedItem().toString());
                fragmentWeightBinding.weightList.setHasFixedSize(true);
                fragmentWeightBinding.weightList.setLayoutManager(new LinearLayoutManager(getContext()));
                fragmentWeightBinding.weightList.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        weightModel = new ArrayList<>(); // Making the arraylist of units

        weightModel.add(new WeightModelClass("Kilogram\t", "kg", "kg", kg));
        weightModel.add(new WeightModelClass("Gram\t", "g", "g", g));
        weightModel.add(new WeightModelClass("Pound\t", "lb", "lb", lb));
        weightModel.add(new WeightModelClass("Ounce\t", "oz", "oz", oz));
        weightModel.add(new WeightModelClass("Carat\t", "carat", "carat", carat));
        weightModel.add(new WeightModelClass("Dram\t", "dram", "dram", dram));
        weightModel.add(new WeightModelClass("Milligram\t", "mg", "mg", mg));
        weightModel.add(new WeightModelClass("Pennyweight\t", "dwt", "dwt", dwt));
        weightModel.add(new WeightModelClass("Grain\t", "grain", "grain", grain));
        weightModel.add(new WeightModelClass("Metric ton\t", "ton", "ton", ton));

        fragmentWeightBinding.setLifecycleOwner(this);

        // Inflate the layout for this fragment
        return fragmentWeightBinding.getRoot();
    }
}