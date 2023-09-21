package com.mastercoding.unitsconverter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.unitsconverter.adapters.fragments.Temperature;
import com.mastercoding.unitsconverter.adapters.fragments.Time;
import com.mastercoding.unitsconverter.adapters.fragments.Volume;
import com.mastercoding.unitsconverter.adapters.fragments.Weight;
import com.mastercoding.unitsconverter.adapters.fragments.Area;
import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.adapters.fragments.Length;
import com.mastercoding.unitsconverter.adapters.fragments.Speed;
import com.mastercoding.unitsconverter.interfaces.ItemClickListener;
import com.mastercoding.unitsconverter.models.MeasurementsModel;

import java.util.ArrayList;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.MyViewHolder> {

    public static ItemClickListener clickListener;
    private final ArrayList<MeasurementsModel> measurementsModels;
    private final Context context;


    /*public void setClickListener(ItemClickListener clickListener) { // Setting a click listener that gets the view and position
        this.clickListener = clickListener;
    }*/

    public MeasurementsAdapter(ArrayList<MeasurementsModel> measurementsModels, Context context) {
        this.measurementsModels = measurementsModels;
        this.context = context;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView measurementName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.measurementName = itemView.findViewById(R.id.measurementName); // Getting the measurement name
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null)
                clickListener.onClick(v, getAbsoluteAdapterPosition());
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(
                R.layout.list_of_measurements,
                parent,
                false
        );
        MeasurementsAdapter.MyViewHolder viewHolder = new MeasurementsAdapter.MyViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MeasurementsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MeasurementsModel model = measurementsModels.get(position); // Getting the position of the new item
        holder.measurementName.setText(model.getName()); // Setting the name of this text



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // This navigates us to the fragment based on the view pressed
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();

                // Find the current fragment by its container ID
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.flContent);

                    if(currentFragment != null) { // To avoid clicking on anything in the main page when a fragment is open

                    } else {
                        switch (model.getName()) {
                            case "Area":
                                Area areaFragment = new Area();
                                navigateToFragment(v.getContext(), areaFragment);
                                break;
                            case "Length/Distance":
                                Length lengthFragment = new Length();
                                navigateToFragment(v.getContext(), lengthFragment);
                                break;
                            case "Temperature":
                                Temperature temperatureFragment = new Temperature();
                                navigateToFragment(v.getContext(), temperatureFragment);
                                break;
                            case "Time":
                                Time timeFragment = new Time();
                                navigateToFragment(v.getContext(), timeFragment);
                                break;
                            case "Velocity/Speed":
                                Speed speedFragment = new Speed();
                                navigateToFragment(v.getContext(), speedFragment);
                                break;
                            case "Volume/Capacity":
                                Volume volumeFragment = new Volume();
                                navigateToFragment(v.getContext(), volumeFragment);
                                break;
                            case "Weight/Mass":
                                Weight weightFragment = new Weight();
                                navigateToFragment(v.getContext(), weightFragment);
                                break;
                            default:
                                Toast.makeText(context, "You pressed on " + model.getName(), Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                }

        });
    }


    @Override
    public int getItemCount() {
        return measurementsModels.size();
    }

    private void navigateToFragment(Context context, Fragment fragment) { // Fragment navigator
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContent, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
