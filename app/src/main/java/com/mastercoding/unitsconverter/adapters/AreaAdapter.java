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
import com.mastercoding.unitsconverter.databinding.ListOfUnitsAreaBinding;
import com.mastercoding.unitsconverter.models.AreaModelClass;

import java.util.ArrayList;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder> {

    private final ArrayList<AreaModelClass> areaModel;
    private final Context context;
    private final ClipboardManager clipboardManager;
    String selectedSpinner;

    public AreaAdapter(ArrayList<AreaModelClass> areaModel, Context context, String selectedSpinnerItem) { // The Adapter constructor, takes ArrayList, context and spinner as parameters
        this.areaModel = areaModel;
        this.context = context;
        this.clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); // For copying to clipboard
        this.selectedSpinner = selectedSpinnerItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListOfUnitsAreaBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_of_units_area,
                parent,
                false
        );
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AreaModelClass model = areaModel.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return areaModel.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListOfUnitsAreaBinding binding;

        MyViewHolder(@NonNull ListOfUnitsAreaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.unitsName.setOnClickListener(this);
        }




        void bind(AreaModelClass model) {
            binding.setViewModel(model); // Setting the view model for the List of units binding (Data Binding)
            binding.executePendingBindings();
            DisplaySavedTextArea(model, model.getAreaSymbols());



            binding.listOfUnitsLayout.setOnClickListener(v -> { // If you click on a view, the value of the number in the Text View will be copied to clipboard
                String textToCopy = String.valueOf(model.getInputValue());
                ClipData clipData = ClipData.newPlainText("label", textToCopy);
                clipboardManager.setPrimaryClip(clipData);
            });

            int maxLength = 10;
            InputFilter[] inputFilters = new InputFilter[1];
            inputFilters[0] = new InputFilter.LengthFilter(maxLength); // Making the max input a user can put in the edit text field to 10 characters.
            EditText areaEditText = ((Activity) context).findViewById(R.id.areaEditText); // assigning the edit text

            areaEditText.setFilters(inputFilters);
            areaEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    DisplaySavedTextArea(model, model.getAreaSymbols());
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    model.updateUnits(selectedSpinner, s.toString()); // Updating the units according to the change in text and the spinner selected

                    SaveText(model.getInputValue(), model.getAreaSymbols());
                    notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    SaveText(model.getInputValue(), model.getAreaSymbols());
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

    private void DisplaySavedTextArea(AreaModelClass model, String unit){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Units", Context.MODE_PRIVATE);
        String unitValue = sharedPreferences.getString(unit, " ");

        // Check if the retrieved value is not empty before updating the model
        if (!unitValue.isEmpty()) {
            model.setInputValue(unitValue);
        }

    }

}
