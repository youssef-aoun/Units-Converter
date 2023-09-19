package com.mastercoding.unitsconverter.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mastercoding.unitsconverter.BR;

public class LengthModelClass extends BaseObservable {

    String lengthUnits;
    String lengthInputValue;
    String lengthSymbols;
    Double lengthUnitsValue;
    boolean signChecker = false;  // checking whether the input is negative or positive, (true for negative).
    double  km = 0.001, m = 1, cm = 100, mm = 1000, yd = 1.093613298, ft = 3.280839895, in = 39.37007874, mile = 0.00062137,
            nmi = 0.000539956, angstrom = 1000000000, barleyCorn = 118.1102362, bolt = 0.0273403, cable = 0.004556722, chain = 0.0497097, cubit = 2.187226597, decimeter = 10;

    public LengthModelClass(String lengthUnits, String lengthSymbols, String inputValue, Double lengthUnitsValue) {
        this.lengthUnits = lengthUnits;
        this.lengthInputValue = inputValue;
        this.lengthSymbols = lengthSymbols;
        this.lengthUnitsValue = lengthUnitsValue;
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getLengthUnits() {
        return lengthUnits;
    }

    public void setLengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
        notifyPropertyChanged(BR.lengthUnits); // notifying the change in unit
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getInputValue() {
        return lengthInputValue;
    }

    public void setInputValue(String inputValue) {
        this.lengthInputValue = inputValue;
        notifyPropertyChanged(BR.inputValue); // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getLengthSymbols() {
        return lengthSymbols;
    }

    public void setLengthSymbols(String lengthSymbols) {
        this.lengthSymbols = lengthSymbols;
        notifyPropertyChanged(BR.lengthSymbols);  // Notifying the change in symbol
    }

    public Double getLengthUnitsValue() {
        return lengthUnitsValue;
    }

    public void lengthUpdateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input
        double valueToBeConverted = 0;
        if(givenValue.length() < 2 && givenValue.startsWith("-")){}
        else
            valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = lengthUnitsValue * valueToBeConverted;
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
            case "km":
                valueGiven /= km;
                break;
            case "m":
                valueGiven /= m;
                break;
            case "cm":
                valueGiven /= cm;
                break;
            case "angstrom":
                valueGiven /= angstrom;
                break;
            case "mm":
                valueGiven /= mm;
                break;
            case "yd":
                valueGiven /= yd;
                break;
            case "ft":
                valueGiven /= ft;
                break;
            case "in":
                valueGiven /= in;
                break;
            case "nmi":
                valueGiven /= nmi;
                break;
            case "barleyCorn":
                valueGiven /= barleyCorn;
                break;
            case "bolt":
                valueGiven /= bolt;
                break;
            case "cable":
                valueGiven /= cable;
                break;
            case "chain":
                valueGiven /= chain;
                break;
            case "cubit":
                valueGiven /= cubit;
                break;
            case "decimeter":
                valueGiven /= decimeter;
                break;
            case "mile":
                valueGiven /= mile;
                break;
        }
        return valueGiven;
    }
}
