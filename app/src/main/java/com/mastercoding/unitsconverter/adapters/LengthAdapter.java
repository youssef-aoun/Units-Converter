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
import com.mastercoding.unitsconverter.databinding.ListOfUnitsAreaBinding;
import com.mastercoding.unitsconverter.databinding.ListOfUnitsLengthBinding;
import com.mastercoding.unitsconverter.models.AreaModelClass;
import com.mastercoding.unitsconverter.models.LengthModelClass;

import java.util.ArrayList;

public class LengthAdapter extends RecyclerView.Adapter<LengthAdapter.LengthViewHolder>{
    private ArrayList<LengthModelClass> lengthModel;
    private Context context;
    private ClipboardManager clipboardManager;
    String selectedSpinner;

    public LengthAdapter(ArrayList<LengthModelClass> lengthModel, Context context, String selectedSpinner) { // The Adapter constructor, takes ArrayList, context and spinner as parameters
        this.lengthModel = lengthModel;
        this.context = context;
        this.clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); // For copying to clipboard
        this.selectedSpinner = selectedSpinner;
    }

    private void lengthUpdateData(String selectedItem){ // Updating the units
        for (LengthModelClass model : lengthModel) {
            model.lengthUpdateUnits(selectedItem, model.getInputValue());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LengthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListOfUnitsLengthBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_of_units_length,
                parent,
                false
        );
        return new LengthAdapter.LengthViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LengthViewHolder holder, int position) {
        LengthModelClass model = lengthModel.get(position);
        holder.bind(model);
    }


    @Override
    public int getItemCount() {
        return lengthModel.size();
    }

    class LengthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListOfUnitsLengthBinding binding;

        LengthViewHolder(@NonNull ListOfUnitsLengthBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.unitsName.setOnClickListener(this);
        }




        void bind(LengthModelClass model) { // Setting the view model for the List of units binding (Data Binding)
            binding.setViewModel(model);
            binding.executePendingBindings();
            DisplaySavedTextLength(model, model.getLengthSymbols());

            binding.listOfUnitsLayout.setOnClickListener(v -> { // If you click on a view, the value of the number in the Text View will be copied to clipboard
                String textToCopy = String.valueOf(model.getInputValue());
                ClipData clipData = ClipData.newPlainText("label", textToCopy);
                clipboardManager.setPrimaryClip(clipData);
            });

            int maxLength = 10;
            InputFilter[] inputFilters = new InputFilter[1];
            inputFilters[0] = new InputFilter.LengthFilter(maxLength); // Making the max input a user can put in the edit text field to 10 characters.
            EditText lengthEditText = ((Activity) context).findViewById(R.id.lengthEditText); // assigning the edit text

            lengthEditText.setFilters(inputFilters);

            lengthEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    model.lengthUpdateUnits(selectedSpinner, s.toString()); // Updating the units according to the change in text and the spinner selected
                    SaveText(model.getInputValue(), model.getLengthSymbols());
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
        editor.commit();
    }

    public void DisplaySavedTextLength(LengthModelClass model, String unit){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Units", Context.MODE_PRIVATE);
        String unitValue = sharedPreferences.getString(unit, " ");

        // Check if the retrieved value is not empty before updating the model
        if (!unitValue.isEmpty()) {
            model.setInputValue(unitValue);
        }

    }
}
