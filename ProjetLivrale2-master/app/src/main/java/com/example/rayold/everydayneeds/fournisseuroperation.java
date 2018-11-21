package com.example.rayold.everydayneeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;


public class fournisseuroperation extends AppCompatActivity {
    TextView service ;
    Button add,delete;
    DatabaseHelper dbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseuroperation);


        dbase = new DatabaseHelper(this);
        String itemsstring = getIntent().getStringExtra("B");
        service= (TextView) findViewById(R.id.textView13);
        service.setText(itemsstring);

        add = (Button)findViewById(R.id.Add);
        delete = (Button)findViewById(R.id.buttonDeletee);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s1 = service.getText().toString();

                if (s1.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(dbase.serviceNameHourlyRate(s1)==true){
                    Toast.makeText(getApplicationContext(), "This service already exists", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean insert = dbase.insertServicefournisseur(s1);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Service Adding Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Service Adding Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = service.getText().toString();
                if(s1.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean remove = dbase.deleteServicefournisseur(service.getText().toString());
                    if (remove == true) {
                        Toast.makeText(getApplicationContext(), "Service delete: Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Service delete: Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}


