package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rayold.everydayneeds.activities.Activity_LoggedIn;
import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.DateAndTime;
import com.example.rayold.everydayneeds.activities.Login;
import com.example.rayold.everydayneeds.activities.User;

public class displayAvailability extends AppCompatActivity {
    String day, hour1,hour2;
    TextView rDay,rHour1,rHour2;
    Button logout,mainMenu, voirService;
    DateAndTime disponible;
    DatabaseHelper db;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_availability);
        db = new DatabaseHelper(this);
        user = db.findUser(getIntent().getStringExtra("EMAIL"));
        disponible = new DateAndTime();
        disponible = db.heureetjour(user.getEmail());

        day = disponible.getDate();
        hour1 = disponible.getTime1();
        hour2 = disponible.getTime2();

        rDay = (TextView)findViewById(R.id.textView7);
        rHour1 = (TextView)findViewById(R.id.textView13);
        rHour2 = (TextView)findViewById(R.id.textView14);
        logout = findViewById(R.id.buttonLogOut);
        mainMenu = findViewById(R.id.mainMenu);
        voirService = findViewById(R.id.button);
        rDay.setText(day);
        rHour1.setText(hour1);
        rHour2.setText(hour2);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(displayAvailability.this, Login.class);
                startActivity(i);
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(displayAvailability.this, ServiceEdit.class);
                i.putExtra("EMAIL", user.getEmail());
                startActivity(i);
            }
        });

        voirService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(displayAvailability.this, FournisseurList.class);
                i.putExtra("EMAIL", user.getEmail());
                startActivity(i);
            }
        });
    }
}
