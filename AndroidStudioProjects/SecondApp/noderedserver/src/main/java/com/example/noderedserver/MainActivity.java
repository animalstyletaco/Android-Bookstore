package com.example.noderedserver;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.noderedserver.MySQLAccess;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivityBtn = findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), NodeRedServer.class);
                startIntent.putExtra("com.example.noderedserver","NodeRedServer Test!");
                startActivity(startIntent);
            }
        });

        Button googleSearchBtn = findViewById(R.id.googleSearchBtn);
        googleSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String google = "http://192.168.1.17:1880";
                Uri webAddress = Uri.parse(google); // http or https need for URI to parse string

                Intent goToNodeRed = new Intent(Intent.ACTION_VIEW, webAddress);
                if (goToNodeRed.resolveActivity(getPackageManager()) != null) { //Research resolve Activity and get Package Manager
                    startActivity(goToNodeRed);
                }

            }
        });

        Button dataBaseConnection = findViewById(R.id.databaseConnectionBtn);
        dataBaseConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLAccess dao = new MySQLAccess();
                try {
                    dao.initializeDataBase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
