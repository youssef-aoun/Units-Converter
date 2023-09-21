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
import com.mastercoding.unitsconverter.databinding.ListOfUnitsVolumeBinding;
import com.mastercoding.unitsconverter.models.VolumeModelClass;

import java.util.ArrayList;

public class VolumeAdapter extends RecyclerView.Adapter<VolumeAdapter.VolumeViewHolder> {

    private final ArrayList<VolumeModelClass> volumeModel;
    private final Context context;
    private final ClipboardManager clipboardManager;
            String selectedSpinner;


    public VolumeAdapter(ArrayList<VolumeModelClass> volumeModel, Context context, String selectedSpinnerItem) { // The Adapter constructor, takes ArrayList, context and spinner as parameters
        this.volumeModel = volumeModel;
        this.context = context;
        this.clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); // For copying to clipboard
        this.selectedSpinner = selectedSpinnerItem;
    }



    @NonNull
    @Override
    public VolumeAdapter.VolumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListOfUnitsVolumeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_of_units_volume,
                parent,
                false
        );
        return new VolumeAdapter.VolumeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VolumeAdapter.VolumeViewHolder holder, int position) {
        VolumeModelClass model = volumeModel.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return volumeModel.size();
    }

    class VolumeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListOfUnitsVolumeBinding binding;

        VolumeViewHolder(@NonNull ListOfUnitsVolumeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.unitsName.setOnClickListener(this);
        }




        void bind(VolumeModelClass model) {
            binding.setViewModel(model); // Setting the view model for the List of units binding (Data Binding)
            binding.executePendingBindings();
            DisplaySavedTextVolume(model, model.getVolumeSymbols()); // displaying the data from the shared preferences

            binding.listOfUnitsLayout.setOnClickListener(v -> { // If you click on a view, the value of the number in the Text View will be copied to clipboard
                String textToCopy = String.valueOf(model.getVolumeInputValue());
                ClipData clipData = ClipData.newPlainText("label", textToCopy);
                clipboardManager.setPrimaryClip(clipData);
            });

            int maxLength = 10;
            InputFilter[] inputFilters = new InputFilter[1];
            inputFilters[0] = new InputFilter.LengthFilter(maxLength); // Making the max input a user can put in the edit text field to 10 characters.
            EditText volumeEditText = ((Activity) context).findViewById(R.id.volumeEditText); // assigning the edit text

            volumeEditText.setFilters(inputFilters);
            volumeEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    model.updateUnits(selectedSpinner, s.toString()); // Updating the units according to the change in text and the spinner selected
                    SaveText(model.getVolumeInputValue(), model.getVolumeSymbols()); // Saving the data to shared preferences
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

    public void DisplaySavedTextVolume(VolumeModelClass model, String unit){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Units", Context.MODE_PRIVATE);
        String unitValue = sharedPreferences.getString(unit, " ");

        // Check if the retrieved value is not empty before updating the model
        if (!unitValue.isEmpty()) {
            model.setVolumeInputValue(unitValue);
        }

    }
}
