package com.mastercoding.unitsconverter.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.databinding.ListOfUnitsTimeBinding;
import com.mastercoding.unitsconverter.models.TimeModelClass;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    private final ArrayList<TimeModelClass> timeModel;
    private final Context context;
    private final ClipboardManager clipboardManager;
    String selectedSpinner;


    public TimeAdapter(ArrayList<TimeModelClass> timeModel, Context context, String selectedSpinner) { // The Adapter constructor, takes ArrayList, context and spinner as parameters
        this.timeModel = timeModel;
        this.context = context;
        this.clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); // For copying to clipboard
        this.selectedSpinner = selectedSpinner;
    }





    @NonNull
    @Override
    public TimeAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListOfUnitsTimeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_of_units_time,
                parent,
                false
        );
        return new TimeAdapter.TimeViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.TimeViewHolder holder, int position) {
        TimeModelClass model = timeModel.get(position);
        holder.bind(model);
    }


    @Override
    public int getItemCount() {
        return timeModel.size();
    }


    class TimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListOfUnitsTimeBinding binding;

        TimeViewHolder(@NonNull ListOfUnitsTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.unitsName.setOnClickListener(this);
        }




        void bind(TimeModelClass model) { // Setting the view model for the List of units binding (Data Binding)
            binding.setViewModel(model);
            binding.executePendingBindings();
            DisplaySavedTextTime(model, model.getTimeSymbols());

            binding.listOfUnitsLayout.setOnClickListener(v -> { // If you click on a view, the value of the number in the Text View will be copied to clipboard
                String textToCopy = String.valueOf(model.getTimeInputValue());
                ClipData clipData = ClipData.newPlainText("label", textToCopy);
                clipboardManager.setPrimaryClip(clipData);
            });

            int maxLength = 10;
            InputFilter[] inputFilters = new InputFilter[1];
            inputFilters[0] = new InputFilter.LengthFilter(maxLength); // Making the max input a user can put in the edit text field to 10 characters.
            EditText timeEditText = ((Activity) context).findViewById(R.id.timeEditText); // assigning the edit text

            timeEditText.setFilters(inputFilters);

            timeEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { // Updating the units according to the change in text and the spinner selected
                    model.timeUpdateUnits(selectedSpinner, s.toString());
                    SaveText(model.getTimeInputValue(), model.getTimeSymbols());
                    notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });



        }


        @Override
        public void onClick(View v) {
            // Handle item click if needed
        }
    }
    private void SaveText(String inputValue, String unit){
        SharedPreferences preferences = context.getSharedPreferences("Units", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //String unitValue = Double.toString(inputValue);
        editor.putString(unit, inputValue);
        editor.apply();
    }

    public void DisplaySavedTextTime(TimeModelClass model, String unit){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Units", Context.MODE_PRIVATE);
        String unitValue = sharedPreferences.getString(unit, " ");

        // Check if the retrieved value is not empty before updating the model
        if (!unitValue.isEmpty()) {
            model.setTimeInputValue(unitValue);
        }

    }

}
