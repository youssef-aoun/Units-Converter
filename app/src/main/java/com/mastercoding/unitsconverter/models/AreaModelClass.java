package com.mastercoding.unitsconverter.models;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.SharedPreferencesKt;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mastercoding.unitsconverter.BR;

public class AreaModelClass extends BaseObservable {

    String areaUnits;
    String areaInputValue;
    String areaSymbols;
    Double areaUnitsValue;
    Context context;
    double km2 = 0.000001, acre = 0.000247105, ha = 0.0001, m2 = 1, cm2 = 10000, mm2 = 1000000, yd2 = 1.196, ft2 = 10.7639, in2 = 1550, mile2 = 0.000000386102;


    boolean signChecker = false;

    public AreaModelClass(String areaUnits, String areaSymbols, String inputValue, Double areaUnitsValue) {
        this.areaUnits = areaUnits;
        this.areaInputValue = inputValue;
        this.areaSymbols = areaSymbols;
        this.areaUnitsValue = areaUnitsValue;
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getAreaUnits() {
        return areaUnits;
    }

    public void setAreaUnits(String areaUnits) {
        this.areaUnits = areaUnits;
        notifyPropertyChanged(BR.areaUnits); // notifying the change in unit
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getInputValue() {
        return areaInputValue;
    }

    public void setInputValue(String inputValue) {
        this.areaInputValue = inputValue;
        notifyPropertyChanged(BR.inputValue); // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getAreaSymbols() {
        return areaSymbols;
    }

    public void setAreaSymbols(String areaSymbols) {
        this.areaSymbols = areaSymbols;
        notifyPropertyChanged(BR.areaSymbols); // Notifying the change in symbol
    }

    public Double getAreaUnitsValue() {
        return areaUnitsValue;
    }

    public void setInputValueFromEditText(String newValue) {
        setInputValue(newValue);
    }
    public void updateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input
        double valueToBeConverted = 0;
        valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = areaUnitsValue * valueToBeConverted;
        String finalData = String.valueOf(finalValue);
        setInputValue(finalData);
    }

    private double converting(String spinnerSelected, String givenValue) { // Converting methods that converts between units and according to the sign in the edittext
        double valueGiven = 0;
        String afterTrim = givenValue;
        if(givenValue.isEmpty())
            return 0.0;
        if(givenValue.charAt(0) == '-' || givenValue.charAt(0) == '_'){
            afterTrim = givenValue.substring(1);
            signChecker = true;
        }
        if(signChecker == true){
            valueGiven = Double.parseDouble(afterTrim);
            valueGiven *= -1;
        }
        else
            valueGiven = Double.parseDouble(givenValue);
        switch (spinnerSelected) {
            case "acre":
                valueGiven /= acre;
                break;
            case "ha":
                valueGiven /= ha;
                break;
            case "m^2":
                valueGiven /= m2;
                break;
            case "cm^2":
                valueGiven /= cm2;
                break;
            case "mm^2":
                valueGiven /= mm2;
                break;
            case "yd^2":
                valueGiven /= yd2;
                break;
            case "ft^2":
                valueGiven /= ft2;
                break;
            case "in^2":
                valueGiven /= in2;
                break;
            case "km^2":
                valueGiven /= km2;
                break;
            case "mile^2":
                valueGiven /= mile2;
                break;
        }
        return valueGiven;
    }




}
