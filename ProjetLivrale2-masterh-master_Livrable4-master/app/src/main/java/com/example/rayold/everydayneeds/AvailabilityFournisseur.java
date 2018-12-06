package com.example.rayold.everydayneeds;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.User;

public class AvailabilityFournisseur extends AppCompatActivity {
    DatabaseHelper db;

    private Button buttonSaveDate, mainMenu;

    int hour1, hour2;

    Spinner mySpinner;

    User user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability_fournisseur);

        db = new DatabaseHelper(this);
        user = db.findUser(getIntent().getStringExtra("EMAIL"));
        buttonSaveDate = (Button) findViewById(R.id.buttonSaveDate);
        mainMenu = (Button) findViewById(R.id.btnMenu);
        mySpinner = (Spinner) findViewById(R.id.spinner);

        final TextView chooseTimedebut = findViewById(R.id.etChooseTimeDebut);

        final TextView chooseTimefin = findViewById(R.id.etChooseTimeFin);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AvailabilityFournisseur.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semaine));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(myAdapter);





        chooseTimedebut.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AvailabilityFournisseur.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override

                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        hour1 = hourOfDay;
                        chooseTimedebut.setText(hourOfDay + ":" + minutes);

                    }

                }, 00, 00, false);

                timePickerDialog.show();

            }

        });

        chooseTimefin.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AvailabilityFournisseur.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override

                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        hour2 = hourOfDay;
                        chooseTimefin.setText(hourOfDay + ":" + minutes);


                    }

                }, 00, 00, false);

                timePickerDialog.show();

            }

        });

        mainMenu.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "information saved: Successfully", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(AvailabilityFournisseur.this, ServiceEdit.class);
                j.putExtra("EMAIL", user.getEmail());
                startActivity(j);
            }
        });


        buttonSaveDate.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String s1 = chooseTimedebut.getText().toString();

                String s2 = chooseTimefin.getText().toString();

                String s3 = mySpinner.getSelectedItem().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("--Choisir jour--")) {

                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();

                }if(hour1 >= hour2){
                    Toast.makeText(getApplicationContext(), "heure que vous choissisez n'est pas valide", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = db.insertAvailable(user.getEmail(),s3,s1,s2);
                    if(insert == true){
                        Toast.makeText(getApplicationContext(), "information saved: Successfully", Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(AvailabilityFournisseur.this, displayAvailability.class);
                        j.putExtra("EMAIL",user.getEmail());
                        startActivity(j);
                    }else{
                        Toast.makeText(getApplicationContext(), "information saved: failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }
}
