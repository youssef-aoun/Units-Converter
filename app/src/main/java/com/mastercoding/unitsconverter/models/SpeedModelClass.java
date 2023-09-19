package com.mastercoding.unitsconverter.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mastercoding.unitsconverter.BR;

public class SpeedModelClass extends BaseObservable {

    String speedUnits;
    String speedInputValue;
    String speedSymbols;
    Double speedUnitsValue;

    boolean signChecker = false; // checking whether the input is negative or positive, (true for negative).
    double ms = 1, kmh = 3.6, mileh = 2.2369363, knot = 1.9438445, cmh = 360000, cmmin = 6000, cms = 100, fth = 11811.02362, ftmin = 196.8503937,
            fts = 3.280834, inh = 141732.2835, inmin = 2362.204724, ins = 39.37007874, mh = 3600, mmin = 60, mms = 1000;


    public SpeedModelClass(String speedUnits, String speedSymbols, String inputValue, Double speedUnitsValue) {
        this.speedUnits = speedUnits;
        this.speedInputValue = inputValue;
        this.speedSymbols = speedSymbols;
        this.speedUnitsValue = speedUnitsValue;
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getSpeedUnits() {
        return speedUnits;
    }

    public void setSpeedUnits(String speedUnits) {
        this.speedUnits = speedUnits;
        notifyPropertyChanged(BR.speedUnits); // notifying the change in unit
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getInputValue() {
        return speedInputValue;
    }

    public void setInputValue(String inputValue) {
        this.speedInputValue = inputValue;
        notifyPropertyChanged(BR.inputValue); // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getSpeedSymbols() {
        return speedSymbols;
    }

    public void setSpeedSymbols(String speedSymbols) {
        this.speedSymbols = speedSymbols;
        notifyPropertyChanged(BR.speedSymbols); // Notifying the change in symbol
    }

    public Double getSpeedUnitsValue() {
        return speedUnitsValue;
    }

    public void speedUpdateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input
        double valueToBeConverted = 0;
        if(givenValue.length() < 2 && givenValue.startsWith("-")){}
        else
            valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = speedUnitsValue * valueToBeConverted;
        String finalData = String.valueOf(finalValue);
        setInputValue(finalData);
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
        if(signChecker == true){
            valueGiven = Double.parseDouble(afterTrim);
            valueGiven *= -1;
        }
        else
            valueGiven = Double.parseDouble(givenValue);
        switch (spinnerSelected) {
            case "m/s":
                valueGiven /= ms;
                break;
            case "km/h":
                valueGiven /= kmh;
                break;
            case "mile/h":
                valueGiven /= mileh;
                break;
            case "knot":
                valueGiven /= knot;
                break;
            case "cm/h":
                valueGiven /= cmh;
                break;
            case "cm/min":
                valueGiven /= cmmin;
                break;
            case "cm/s":
                valueGiven /= cms;
                break;
            case "ft/h":
                valueGiven /= fth;
                break;
            case "ft/min":
                valueGiven /= ftmin;
                break;
            case "ft/s":
                valueGiven /= fts;
                break;
            case "in/h":
                valueGiven /= inh;
                break;
            case "in/min":
                valueGiven /= inmin;
                break;
            case "in/s":
                valueGiven /= ins;
                break;
            case "m/h":
                valueGiven /= mh;
                break;
            case "m/min":
                valueGiven /= mmin;
                break;
            case "mm/s":
                valueGiven /= mms;
                break;
        }
        return valueGiven;
    }

}
