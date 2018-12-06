package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rayold.everydayneeds.activities.Login;

public class searchChoice extends AppCompatActivity {
    Button searchTime,searchRate, service,logg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);
        searchTime = (Button) findViewById(R.id.etsearchTime);
        searchRate= (Button) findViewById(R.id.button4);
        service = (Button) findViewById(R.id.searchService);
        logg = (Button) findViewById(R.id.button5);
        searchTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(searchChoice.this, searchByTime.class);
                startActivity(i);
            }
        });
        searchRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(searchChoice.this, searchByRate.class);
                startActivity(i);
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(searchChoice.this, ServiceList2.class);
                startActivity(i);
            }
        });

        logg.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent i = new Intent(searchChoice.this, Login.class);
                startActivity(i);
            }
        });

    }
}
