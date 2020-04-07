package com.example.lab2android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static String PREFS_NAME = "PREFS";


    String description = "";
    public static final String EXTRA_MESSAGE = "com.example.lab2android.MESSAGE";
    public String[] productsTitle = new String[]{
            "PC",
            "TV",
            "Laptop"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean darkmode = load_preferences();
        if (darkmode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            System.out.println("hahahah\n\n\n\n");
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        final ListView productsList = (ListView) findViewById(R.id.productsList);
        final TextView price = (TextView) findViewById(R.id.price);


        ArrayList<String> products = new ArrayList<>(Arrays.asList(productsTitle));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, products);

        productsList.setAdapter(arrayAdapter);
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProduct = (String) productsList.getItemAtPosition(position);
                String priceTag = "";
                switch (selectedProduct) {
                    case "PC":
                        priceTag = "1000$";
                        break;
                    case "TV":
                        priceTag = "400$";
                        break;
                    case "Laptop":
                        priceTag = "800$";
                        break;
                    default:
                        break;
                }
                price.setText("The price is " + priceTag);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart invoked");
    }

    @Override
    protected void onSaveInstanceState(Bundle onSaveState) {

        TextView price = (TextView) findViewById(R.id.price);
        description = price.getText().toString();
        onSaveState.putString("price", description);
        super.onSaveInstanceState(onSaveState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle onRestoreState) {
        super.onRestoreInstanceState(onRestoreState);
        TextView price = (TextView) findViewById(R.id.price);
        description = onRestoreState.getString("price");
        price.setText(description);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // lab 3 implementari menu items + new activities(login) with intent filter
    public boolean login(MenuItem menuItem) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        return true;
    }

    // AlertDialog cauta daca exista produsul apoi afiseaza alt dialog in care da raspunsul
    public boolean search(MenuItem menuItem) {
        SearchDialog searchDialog = new SearchDialog();
        searchDialog.show(getSupportFragmentManager(), "search dialog");
        return true;
    }

    // LAB4 shared preferences
    public boolean settings(MenuItem menuItem) {
        Intent intent = new Intent();
        intent.setClassName(this, "com.example.lab2android.SettingsActivity");
        startActivity(intent);
        return true;
    }

    public boolean sensors(MenuItem menuItem) {
        Intent intent = new Intent(this, SensorsActivity.class);
        startActivity(intent);
        return true;
    }

    public boolean camera(MenuItem menuItem) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
        return true;
    }

    public boolean load_preferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkmode = sharedPreferences.getBoolean("darkmode", false);
        String username = sharedPreferences.getString("username", "noone");

        Toast.makeText(this, "Username: " + username + "\nDarkMode: " + darkmode, Toast.LENGTH_SHORT).show();
        return darkmode;
    }

    public boolean loadLastSearch(MenuItem menuItem) {
        Intent intent = new Intent(this, RememberActivity.class);
        startActivity(intent);
        return true;
    }


}