package com.mastercoding.unitsconverter.fragments;

import static android.R.layout.simple_spinner_item;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
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
import com.mastercoding.unitsconverter.adapters.WeightAdapter;
import com.mastercoding.unitsconverter.databinding.FragmentWeightBinding;
import com.mastercoding.unitsconverter.models.WeightModelClass;

import java.util.ArrayList;

public class Weight extends Fragment {


    Spinner spinnerFrom;
    String selectedSpinnerItem = "";

    WeightAdapter adapter;
    ArrayList<WeightModelClass> weightModel;


    private FragmentWeightBinding fragmentWeightBinding;

    double g = 1000, kg = 1, lb = 2.2046226, oz = 35.273962, carat = 5000, dram = 564.3833912, mg = 1000000, dwt = 643.0147943, grain = 15432.35835,
            ton = 0.001, tonUK = 0.0009842065276, tonUS = 0.001102311, cwtUK = 0.019684131, cwtUS = 0.022046226, lbTROY = 2.679228309, ozTROY = 32.15073971,
            st = 0.157473044, slug = 0.068521766;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentWeightBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weight, container, false); // Has to be done instead of the view for the usage of databinding
        fragmentWeightBinding.getRoot().clearFocus();

        ArrayList<String> arrayListFrom = new ArrayList<>(); // Filling an arraylist of strings that will be put in a spinner

        arrayListFrom.add("g");
        arrayListFrom.add("kg");
        arrayListFrom.add("lb");
        arrayListFrom.add("oz");
        arrayListFrom.add("carat");
        arrayListFrom.add("dram");
        arrayListFrom.add("mg");
        arrayListFrom.add("dwt");
        arrayListFrom.add("grain");
        arrayListFrom.add("ton");
        arrayListFrom.add("tonUK");
        arrayListFrom.add("tonUS");
        arrayListFrom.add("cwtUK");
        arrayListFrom.add("cwtUS");
        arrayListFrom.add("lbTROY");
        arrayListFrom.add("ozTROY");
        arrayListFrom.add("st");
        arrayListFrom.add("slug");

        spinnerFrom = (Spinner) fragmentWeightBinding.weightUnitsSpinner;  // Getting the adapter from the xml
        ArrayAdapter<String> arrayAdapterFrom = new ArrayAdapter<>(getContext(), simple_spinner_item, arrayListFrom);
        spinnerFrom.setAdapter(arrayAdapterFrom); // setting the adapter for the spinner
        arrayAdapterFrom.setDropDownViewResource(simple_spinner_item);


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
        weightModel.add(new WeightModelClass("Long ton (UK)\t", "ton (UK)", "ton (UK)", tonUK));
        weightModel.add(new WeightModelClass("Short ton (US)\t", "ton (US)", "ton (US)", tonUS));
        weightModel.add(new WeightModelClass("Long hundredweight (UK)\t", "cwt (UK)", "cwt (UK)", cwtUK));
        weightModel.add(new WeightModelClass("Short hundredweight (US)\t", "cwt (US)", "cwt (US)", cwtUS));
        weightModel.add(new WeightModelClass("Pound (Troy)\t", "lb (Troy)", "lb (Troy)", lbTROY));
        weightModel.add(new WeightModelClass("Ounce (Troy)\t", "oz (Troy)", "oz (Troy)", ozTROY));
        weightModel.add(new WeightModelClass("Stone\t", "st", "st", st));
        weightModel.add(new WeightModelClass("Slug\t", "slug", "slug", slug));


        fragmentWeightBinding.setLifecycleOwner(this);

        // Inflate the layout for this fragment
        return fragmentWeightBinding.getRoot();
    }
}