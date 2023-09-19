package com.mastercoding.unitsconverter.models;

public class MeasurementsModel {
    private String measurementName;

    public MeasurementsModel(String name) {
        this.measurementName = name;
    }

    public String getName() {
        return measurementName;
    }

    public void setName(String name) {
        this.measurementName = name;
    }
}
