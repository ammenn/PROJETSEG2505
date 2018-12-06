package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RatingService extends AppCompatActivity {
    Button submitt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_service);
        submitt = (Button)findViewById(R.id.button2);

        submitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RatingService.this, searchChoice.class);
                Toast.makeText(getApplicationContext(), "Rating successfull", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }
}
