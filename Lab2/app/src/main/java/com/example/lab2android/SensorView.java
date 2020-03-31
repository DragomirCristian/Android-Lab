package com.example.lab2android;

import android.hardware.Sensor;

import java.util.Arrays;

public class SensorView {
    private Sensor sensor;
    private float[] values;

    public SensorView(Sensor sensor, float[] values) {
        this.sensor = sensor;
        this.values = values;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "sensor=" + sensor.getName() +
                ", values=" + Arrays.toString(values);
    }
}
