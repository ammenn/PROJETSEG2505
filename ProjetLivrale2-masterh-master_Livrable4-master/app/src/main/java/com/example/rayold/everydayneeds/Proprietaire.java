package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.DateAndTime;
import com.example.rayold.everydayneeds.activities.User;

public class Proprietaire extends AppCompatActivity {
    DatabaseHelper db;
    TextView fournisseur, jour, time1, time2, service, cost;
    Button book;
    User user;
    DateAndTime dayHour;
    Service service1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprietaire);

        db = new DatabaseHelper(this);
        fournisseur = (TextView) findViewById(R.id.etEmailFournisseur);
        jour = (TextView) findViewById(R.id.etJourDisponible);
        time1 = (TextView) findViewById(R.id.etHeureDebut);
        time2 = (TextView) findViewById(R.id.etHeureFin);
        service = (TextView) findViewById(R.id.etService);
        cost = (TextView) findViewById(R.id.etCost);

        user = db.findUser(getIntent().getStringExtra("EMAIL"));
        dayHour = db.heureetjour(user.getEmail());
        service1= db.serviceInformation(getIntent().getStringExtra("SERVICE"));

        fournisseur.setText(user.getEmail());
        jour.setText(dayHour.getDate());
        time1.setText(dayHour.getTime1());
        time2.setText(dayHour.getTime2());
        service.setText(service1.getServiceName());
        cost.setText(service1.getServiceCost());

        book = (Button) findViewById(R.id.buttonAdd);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Proprietaire.this, RatingService.class);
                Toast.makeText(getApplicationContext(), "Your service has been booked Successfully", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
    });}

}
