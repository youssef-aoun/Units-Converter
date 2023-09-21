package com.mastercoding.unitsconverter.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class WeightModelClass extends BaseObservable {

    String weightUnits;
    String weightInputValue;
    String weightSymbols;
    Double weightUnitsValue;

    double g = 1000, kg = 1, lb = 2.2046226, oz = 35.273962, carat = 5000, dram = 564.3833912, mg = 1000000, dwt = 643.0147943, grain = 15432.35835,
            ton = 0.001;


    public WeightModelClass(String weightUnits, String weightSymbols, String weightInputValue, Double weightUnitsValue) {
        this.weightUnits = weightUnits;
        this.weightInputValue = weightInputValue;
        this.weightSymbols = weightSymbols;
        this.weightUnitsValue = weightUnitsValue;
    }


    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.weightUnits); // notifying the change in unit
    }


    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getWeightInputValue() {
        return weightInputValue;
    }

    public void setInputValue(String weightInputValue) {
        this.weightInputValue = weightInputValue;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.weightInputValue);  // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getWeightSymbols() {
        return weightSymbols;
    }

    public void setWeightSymbols(String weightSymbols) {
        this.weightSymbols = weightSymbols;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.weightSymbols); // Notifying the change in symbol
    }

    public Double getWeightUnitsValue() {
        return weightUnitsValue;
    }

    public void weightUpdateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input

        double valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = weightUnitsValue * valueToBeConverted;
        String finalData = String.valueOf(finalValue);
        setInputValue(finalData);
    }



    private double converting(String spinnerSelected, String givenValue) { // Converting methods that converts between units
        if(givenValue.isEmpty())
            return 0.0;
        double valueGiven = Double.parseDouble(givenValue);
        switch (spinnerSelected) {
            case "g":
                valueGiven /= g;
                break;
            case "kg":
                valueGiven /= kg;
                break;
            case "lb":
                valueGiven /= lb;
                break;
            case "oz":
                valueGiven /= oz;
                break;
            case "carat":
                valueGiven /= carat;
                break;
            case "dram":
                valueGiven /= dram;
                break;
            case "mg":
                valueGiven /= mg;
                break;
            case "dwt":
                valueGiven /= dwt;
                break;
            case "grain":
                valueGiven /= grain;
                break;
            case "ton":
                valueGiven /= ton;
                break;
        }
        return valueGiven;
    }
}
