package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.Activity_LoggedIn;
import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.Login;
import com.example.rayold.everydayneeds.activities.User;

public class ServiceEdit extends AppCompatActivity {
    EditText w;
    Button addService,deleteService, logOut, bListView;
    DatabaseHelper db;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_edit);


        db = new DatabaseHelper(this);
        w= (EditText)findViewById(R.id.editServiceServiceFournisseur);
        addService = (Button)findViewById(R.id.buttonAddServiceFournisseur);
        logOut = findViewById(R.id.logOut);
        deleteService = findViewById(R.id.button3);
        bListView = findViewById(R.id.serviceListFournisseur);
        user = db.findUser(getIntent().getStringExtra("EMAIL"));

        addService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceEdit.this, serviceList.class);
                i.putExtra("EMAIL", user.getEmail());
                startActivity(i);
            }
        });

        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = w.getText().toString();
                if(s1.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean remove = db.deleteServiceFournisseur(w.getText().toString(),user.getEmail());
                    if (remove == true) {
                        Toast.makeText(getApplicationContext(), "Service delete: Successful", Toast.LENGTH_SHORT).show();
                        w.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Service delete: Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceEdit.this, Login.class);
                startActivity(i);
            }
        });

        bListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServiceEdit.this, FournisseurList.class);
                i.putExtra("EMAIL", user.getEmail());
                startActivity(i);
            }
        });
    }
}
