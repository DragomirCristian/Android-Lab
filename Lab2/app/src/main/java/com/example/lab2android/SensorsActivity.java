package com.example.lab2android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;

public class SensorsActivity extends AppCompatActivity implements LocationListener, SensorEventListener {
    private SensorManager sensorManager;
    private LocationManager locationManager;
    private List<Sensor> sensorList;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SensorsActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int id = sensorEvent.sensor.getName().length();
        TextView textView = (TextView) findViewById(id);
        textView.setText(new SensorView(sensorEvent.sensor, sensorEvent.values).toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onLocationChanged(Location location) {
        ((TextView) findViewById(R.id.location_text)).setText("Latitude:" + location.getLatitude() + "\nLongitude:" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, INTERNET}, 1);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        List<Sensor> sensor = sensorManager.getSensorList(Sensor.TYPE_ALL);

        LinearLayout dinamic = (LinearLayout) findViewById(R.id.dinamicLayout);
        for (Sensor sens : sensor) {
            sensorManager.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams);
            textView.setId(sens.getName().length());
            textView.setText(sens.toString());
            dinamic.addView(textView);
        }

        if (ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toast = new Toast(this);
            toast.setText("Need more permissions");
            onBackPressed();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
        ((TextView) findViewById(R.id.location_text)).setText("location");
    }
}