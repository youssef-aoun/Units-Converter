package com.mastercoding.unitsconverter.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mastercoding.unitsconverter.BR;

public class VolumeModelClass extends BaseObservable {

    String volumeUnits;
    String volumeInputValue;
    String volumeSymbols;
    Double volumeUnitsValue;

    double m3 = 1, l = 1000, cm3 = 1000000, ml = 1000000, mm3 = 1000000000, tblspn = 66666.66667, tspn = 200000, ft3 = 35.31466672, in3 = 61023.74409, yd3 = 1.307950619,
            usBarrel = 6.28981077, usGallon = 264.1720524, usDryGallon = 227.0207461, usFlOz = 33814.0227, usTblspn = 202884.1362, usTspn = 202884.1362,
            ukBarrel = 6.1103, ukGallon = 219.9692483, ukFlOz = 35195.07973;


    public VolumeModelClass(String volumeUnits, String volumeInputValue, String volumeSymbols, Double volumeUnitsValue) {
        this.volumeUnits = volumeUnits;
        this.volumeInputValue = volumeInputValue;
        this.volumeSymbols = volumeSymbols;
        this.volumeUnitsValue = volumeUnitsValue;
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getVolumeUnits() {
        return volumeUnits;
    }

    public void setVolumeUnits(String volumeUnits) {
        this.volumeUnits = volumeUnits;
        notifyPropertyChanged(BR.volumeUnits); // notifying the change in unit
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getVolumeInputValue() {
        return volumeInputValue;
    }

    public void setVolumeInputValue(String volumeInputValue) {
        this.volumeInputValue = volumeInputValue;
        notifyPropertyChanged(BR.volumeInputValue); // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getVolumeSymbols() {
        return volumeSymbols;
    }

    public void setVolumeSymbols(String volumeSymbols) {
        this.volumeSymbols = volumeSymbols;
        notifyPropertyChanged(BR.volumeSymbols); // Notifying the change in symbol
    }

    public Double getVolumeUnitsValue() {
        return volumeUnitsValue;
    }

    public void setInputValueFromEditText(String newValue) {
        setVolumeInputValue(newValue);
    }


    public void updateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input
        double valueToBeConverted = 0;
        valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = volumeUnitsValue * valueToBeConverted;
        String finalData = String.valueOf(finalValue);
        setVolumeInputValue(finalData);
    }

    private double converting(String spinnerSelected, String givenValue) { // Converting methods that converts between units and according to the sign in the edittext
        double valueGiven = 0;
        String afterTrim = givenValue;
        if(givenValue.isEmpty())
            return 0.0;
        valueGiven = Double.parseDouble(givenValue);
        switch (spinnerSelected) {
            case "m^3":
                valueGiven /= m3;
                break;
            case "l":
                valueGiven /= l;
                break;
            case "ml":
                valueGiven /= ml;
                break;
            case "cm^3":
                valueGiven /= cm3;
                break;
            case "mm^3":
                valueGiven /= mm3;
                break;
            case "yd^3":
                valueGiven /= yd3;
                break;
            case "ft^3":
                valueGiven /= ft3;
                break;
            case "in^3":
                valueGiven /= in3;
                break;
            case "tblspn":
                valueGiven /= tblspn;
                break;
            case "tspn":
                valueGiven /= tspn;
                break;
            case "US Barrel":
                valueGiven /= usBarrel;
                break;
            case "US Gallon":
                valueGiven /= usGallon;
                break;
            case "US Dry Gallon":
                valueGiven /= usDryGallon;
                break;
            case "US Fl Oz":
                valueGiven /= usFlOz;
                break;
            case "US tblspn":
                valueGiven /= usTblspn;
                break;
            case "US tspn":
                valueGiven /= usTspn;
                break;
            case "UK Barrel":
                valueGiven /= ukBarrel;
                break;
            case "UK Gallon":
                valueGiven /= ukGallon;
                break;
            case "UK Fl Oz":
                valueGiven /= ukFlOz;
                break;

        }
        return valueGiven;
    }
}
