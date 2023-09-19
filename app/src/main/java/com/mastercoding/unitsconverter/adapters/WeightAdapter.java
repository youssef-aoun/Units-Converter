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

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.unitsconverter.R;
import com.mastercoding.unitsconverter.databinding.ListOfUnitsWeightBinding;
import com.mastercoding.unitsconverter.models.WeightModelClass;

import java.util.ArrayList;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightViewHolder>{
    private final ArrayList<WeightModelClass> weightModel;
    private final Context context;
    private final ClipboardManager clipboardManager;
    String selectedSpinner;

    public WeightAdapter(ArrayList<WeightModelClass> weightModel, Context context, String selectedSpinner) { // The Adapter constructor, takes ArrayList, context and spinner as parameters
        this.weightModel = weightModel;
        this.context = context;
        this.clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); // For copying to clipboard
        this.selectedSpinner = selectedSpinner;
    }


    @NonNull
    @Override
    public WeightAdapter.WeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListOfUnitsWeightBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_of_units_weight,
                parent,
                false
        );
        return new WeightAdapter.WeightViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull WeightAdapter.WeightViewHolder holder, int position) {
        WeightModelClass model = weightModel.get(position);
        holder.bind(model);
    }


    @Override
    public int getItemCount() {
        return weightModel.size();
    }


    class WeightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListOfUnitsWeightBinding binding;

        WeightViewHolder(@NonNull ListOfUnitsWeightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.unitsName.setOnClickListener(this);
        }




        void bind(WeightModelClass model) { // Setting the view model for the List of units binding (Data Binding)
            binding.setViewModel(model);
            binding.executePendingBindings();
            DisplaySavedTextWeight(model, model.getWeightSymbols());

            binding.listOfUnitsLayout.setOnClickListener(v -> { // If you click on a view, the value of the number in the Text View will be copied to clipboard
                String textToCopy = String.valueOf(model.getWeightInputValue());
                ClipData clipData = ClipData.newPlainText("label", textToCopy);
                clipboardManager.setPrimaryClip(clipData);
            });

            int maxLength = 10;
            InputFilter[] inputFilters = new InputFilter[1];
            inputFilters[0] = new InputFilter.LengthFilter(maxLength); // Making the max input a user can put in the edit text field to 10 characters.
            EditText weightEditText = ((Activity) context).findViewById(R.id.weightEditText); // assigning the edit text

            weightEditText.setFilters(inputFilters);

            weightEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { // Updating the units according to the change in text and the spinner selected
                    model.weightUpdateUnits(selectedSpinner, s.toString());
                    SaveText(model.getWeightInputValue(), model.getWeightSymbols());
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

    public void DisplaySavedTextWeight(WeightModelClass model, String unit){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Units", Context.MODE_PRIVATE);
        String unitValue = sharedPreferences.getString(unit, " ");

        // Check if the retrieved value is not empty before updating the model
        if (!unitValue.isEmpty()) {
            model.setInputValue(unitValue);
        }

    }
}
