package com.example.harwardsmiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class Forgot extends AppCompatActivity {
EditText email;
ImageView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        email = findViewById(R.id.res_email);
        res = findViewById(R.id.but_reset);
    }
}
