package com.mastercoding.unitsconverter.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class TemperatureModelClass extends BaseObservable {

    String temperatureUnits;
    String temperatureInputValue;
    String temperatureSymbols;
    Double temperatureUnitsValue;
    boolean signChecker = false; // checking whether the input is negative or positive, (true for negative).

    double celsius = 0, kelvin = 273.15;


    public TemperatureModelClass(String temperatureUnits, String temperatureSymbols, String temperatureInputValue,  Double temperatureUnitsValue) {
        this.temperatureUnits = temperatureUnits;
        this.temperatureInputValue = temperatureInputValue;
        this.temperatureSymbols = temperatureSymbols;
        this.temperatureUnitsValue = temperatureUnitsValue;
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getTemperatureUnits() {
        return temperatureUnits;
    }

    public void setTemperatureUnits(String temperatureUnits) {
        this.temperatureUnits = temperatureUnits;
        notifyPropertyChanged(BR.temperatureUnits); // notifying the change in unit
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getTemperatureInputValue() {
        return temperatureInputValue;
    }

    public void setTemperatureInputValue(String temperatureInputValue) {
        this.temperatureInputValue = temperatureInputValue;
        notifyPropertyChanged(BR.temperatureInputValue); // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getTemperatureSymbols() {
        return temperatureSymbols;
    }

    public void setTemperatureSymbols(String temperatureSymbols) {
        this.temperatureSymbols = temperatureSymbols;
        notifyPropertyChanged(BR.temperatureSymbols);// Notifying the change in symbol
    }

    public Double getTemperatureUnitsValue() {
        return temperatureUnitsValue;
    }

    public void temperatureUpdateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input
        double valueToBeConverted = 0;
        if(givenValue.length() < 2 && givenValue.startsWith("-")){}
        else
            valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = temperatureUnitsValue * valueToBeConverted;

        // The below if else statement is a code unique for temperature because the units don't have a unique value, they are formulas instead.

        if (temperatureUnitsValue == 0) {
            finalValue = valueToBeConverted + temperatureUnitsValue;
        } else if (temperatureUnitsValue == 32) {
            finalValue = (valueToBeConverted * 1.8) + temperatureUnitsValue;
        } else if (temperatureUnitsValue == 273.15) {
            finalValue = valueToBeConverted + temperatureUnitsValue;
        } else if (temperatureUnitsValue == 491.67) {
            finalValue = (valueToBeConverted * 1.8) + temperatureUnitsValue;
        }

        String finalData = String.valueOf(finalValue);
        setTemperatureInputValue(finalData);
    }

    private double converting(String spinnerSelected, String givenValue) { // Converting methods that converts between units and according to the sign in the edittext
        signChecker = false;
        double valueGiven = 0;
        String afterTrim = givenValue;
        if(givenValue.isEmpty())
            return 0.0;
        if(givenValue.charAt(0) == '-' || givenValue.charAt(0) == '_'){
            afterTrim = givenValue.substring(1);
            signChecker = true;
        }
        if(signChecker){
            valueGiven = Double.parseDouble(afterTrim);
            valueGiven *= -1;
        }
        else
            valueGiven = Double.parseDouble(givenValue);
        switch (spinnerSelected) {
            case "celsius":
                valueGiven -= (celsius);
                break;
            case "fahrenheit":
                valueGiven = (valueGiven-32)/1.8;
                break;
            case "kelvin":
                valueGiven -= (kelvin);
                break;
            case "rankine":
                valueGiven = (valueGiven-491.67)/1.8;
                break;
        }
        return valueGiven;
    }


}
