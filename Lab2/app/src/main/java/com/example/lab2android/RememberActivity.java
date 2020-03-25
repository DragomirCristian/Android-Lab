package com.example.lab2android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RememberActivity extends AppCompatActivity {
    private static final String FILE_NAME = "history.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        TextView lastSearch = (TextView) findViewById(R.id.lastSearchView);
        lastSearch.setText(loadText());
    }

    // LAB 5 storage
    public void saveText(String text) {
        try {
            FileOutputStream os = openFileOutput(FILE_NAME, 0);
            os.write(text.getBytes());
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadText() {
        String text = "";
        try {
            FileInputStream is = openFileInput(FILE_NAME);
            int i = 0;

            while ((i = is.read()) != -1) {
                text = text + Character.toString((char) i);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    public void remember(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        saveText(editText.getText().toString());
        TextView lastSearch = (TextView) findViewById(R.id.lastSearchView);
        lastSearch.setText(loadText());
    }

}
