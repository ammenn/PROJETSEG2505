package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;

import java.util.ArrayList;

public class FournisseurListForEachService extends AppCompatActivity {
    DatabaseHelper db;
    String serviceName;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur_list_for_each_service);

        final ListView listView = (ListView) findViewById(R.id.serviceListView);
        db = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getFournisseurService(getIntent().getStringExtra("SERVICE"));
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no fournisseur available for this service", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
                adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        theList);
                listView.setAdapter(adapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent j = new Intent(FournisseurListForEachService.this, Proprietaire.class);
                j.putExtra("EMAIL",(String) listView.getItemAtPosition(position));
                j.putExtra("SERVICE",getIntent().getStringExtra("SERVICE"));
                startActivity(j);
            }
        });
    }
}
