package com.mastercoding.unitsconverter.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class TimeModelClass extends BaseObservable {

    String timeUnits;
    String timeInputValue;
    String timeSymbols;
    Double timeUnitsValue;

    double minute = 60, hour = 1, second = 3600, week = 0.005952381, year = 0.00011408, century = 0.00000114079552, decade = 0.0000114079552,
            millisecond = 3000000;
    double day = hour/24;

    public TimeModelClass(String timeUnits, String timeSymbols,  String timeInputValue, Double timeUnitsValue) {
        this.timeUnits = timeUnits;
        this.timeInputValue = timeInputValue;
        this.timeSymbols = timeSymbols;
        this.timeUnitsValue = timeUnitsValue;
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getTimeUnits() {
        return timeUnits;
    }

    public void setTimeUnits(String timeUnits) {
        this.timeUnits = timeUnits;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.timeUnits); // notifying the change in unit
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getTimeInputValue() {
        return timeInputValue;
    }

    public void setTimeInputValue(String timeInputValue) {
        this.timeInputValue = timeInputValue;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.timeInputValue); // notifying the change in input
    }

    // Making the class bindable so we could use it in data binding
    @Bindable
    public String getTimeSymbols() {
        return timeSymbols;
    }

    public void setTimeSymbols(String timeSymbols) {
        this.timeSymbols = timeSymbols;
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.timeSymbols); // Notifying the change in symbol
    }

    public Double getTimeUnitsValue() {
        return timeUnitsValue;
    }

    public void timeUpdateUnits(String spinnerSelected, String givenValue){ // Updating units according to spinner selected and the value given in the input

        double valueToBeConverted = converting(spinnerSelected, givenValue);
        double finalValue = timeUnitsValue * valueToBeConverted;
        String finalData = String.valueOf(finalValue);
        setTimeInputValue(finalData);
    }

    private double converting(String spinnerSelected, String givenValue) { // Converting methods that converts between units
        if(givenValue.isEmpty())
            return 0.0;
        double valueGiven = Double.parseDouble(givenValue);
        switch (spinnerSelected) {
            case "day":
                valueGiven /= day;
                break;
            case "minute":
                valueGiven /= minute;
                break;
            case "hour":
                valueGiven /= hour;
                break;
            case "second":
                valueGiven /= second;
                break;
            case "week":
                valueGiven /= week;
                break;
            case "year":
                valueGiven /= year;
                break;
            case "century":
                valueGiven /= century;
                break;
            case "decade":
                valueGiven /= decade;
                break;
            case "millisecond":
                valueGiven /= millisecond;
                break;
            default:
                valueGiven = 0;
                break;
        }
        return valueGiven;
    }
}
