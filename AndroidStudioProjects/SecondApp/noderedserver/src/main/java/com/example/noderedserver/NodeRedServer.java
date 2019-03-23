package com.example.noderedserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NodeRedServer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_red_server);

        if (getIntent().hasExtra("com.example.noderedserver")) {
            TextView tv = findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("com.example.noderedserver");
            tv.setText(text); // outputs Hello World on Second Activity
        }
    }
}