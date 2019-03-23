package com.example.secondapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //checks for intent from main activity
        if(getIntent().hasExtra("com.example.secondapp")){
            TextView tv = findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("com.example.secondapp");
            tv.setText(text); // outputs Hello World on Second Activity
        }
    }
}
