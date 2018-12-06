package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class searchByRate extends AppCompatActivity {
    Button searchByRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rate);
        searchByRate =(Button)findViewById(R.id.button2);

        searchByRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(searchByRate.this, ServiceList2.class);
                startActivity(i);
            }
        });
    }
}
