package com.mastercoding.unitsconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.mastercoding.unitsconverter.adapters.MeasurementsAdapter;
import com.mastercoding.unitsconverter.fragments.Area;
import com.mastercoding.unitsconverter.models.MeasurementsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context;

    ArrayList<MeasurementsModel> models;
    private Toolbar toolbar;

    MeasurementsAdapter adapter;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        toolbar = findViewById(R.id.toolbar); // Making a toolbar for the application
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        recyclerView = (RecyclerView) findViewById(R.id.listOfConversion); // Setting the list of measurements

        models = new ArrayList<>();
        models.add(new MeasurementsModel("Area"));
        models.add(new MeasurementsModel("Length/Distance"));
        models.add(new MeasurementsModel("Temperature"));
        models.add(new MeasurementsModel("Time"));
        models.add(new MeasurementsModel("Velocity/Speed"));
        models.add(new MeasurementsModel("Volume/Capacity"));
        models.add(new MeasurementsModel("Weight/Mass"));

        adapter = new MeasurementsAdapter(models, this); // assigning the adapter to the list

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}