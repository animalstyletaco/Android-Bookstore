package com.example.secondapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivityBtn = findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class);
                startIntent.putExtra("com.example.secondapp","Hello World!");
                startActivity(startIntent);
            }
        });

        Button googleSearchBtn = findViewById(R.id.googleSearchBtn);
        googleSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String google = "http://www.google.com";
                Uri webAddress = Uri.parse(google);

                Intent goToGoogle = new Intent(Intent.ACTION_VIEW, webAddress);
                if (goToGoogle.resolveActivity(getPackageManager()) != null) { //Research resolve Activity and get Package Manager
                    goToGoogle.resolveActivity(getPackageManager());
                    startActivity(goToGoogle);
                }
            }
        });
    }
}
