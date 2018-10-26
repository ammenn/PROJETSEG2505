package com.example.rayold.everydayneeds.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rayold.everydayneeds.R;

public class Activity_LoggedIn extends AppCompatActivity {
    Button logout;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__logged_in);

        String email = getIntent().getStringExtra("EMAIL");
        TextView textView = (TextView)findViewById(R.id.Email);
        TextView name = (TextView)findViewById(R.id.Name);
        TextView role = (TextView)findViewById(R.id.Role);
        textView.setText(email);


        logout = (Button)findViewById(R.id.bLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_LoggedIn.this, Login.class);
                startActivity(i);
            }
        });
    }
}
