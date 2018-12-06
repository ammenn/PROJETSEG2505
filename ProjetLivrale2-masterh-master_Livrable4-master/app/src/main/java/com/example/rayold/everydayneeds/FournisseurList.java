package com.example.rayold.everydayneeds;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.User;

import java.util.ArrayList;

public class FournisseurList extends AppCompatActivity {
    DatabaseHelper db;
    String serviceName;
    User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fournisseur_list);
        final ListView listView = (ListView) findViewById(R.id.serviceListFournisseur);
        db = new DatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        user = db.findUser(getIntent().getStringExtra("EMAIL"));
        Cursor data = db.getServiceFournisseur(user.getEmail());
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no service available. Admin didn't add any yet", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}