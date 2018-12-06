package com.example.rayold.everydayneeds;
//package com.example.rayold.everydayneeds.actionbarsearchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.example.rayold.everydayneeds.activities.User;

import java.util.ArrayList;

public class serviceList extends AppCompatActivity {
    DatabaseHelper db;
    String serviceName;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        final ListView listView = (ListView) findViewById(R.id.serviceListView);
        db = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getService();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no service available. Admin didn't add any yet", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
//                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
//                listView.setAdapter(listAdapter);

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
                serviceName = (String) listView.getItemAtPosition(position);
                User user = db.findUser(getIntent().getStringExtra("EMAIL"));

                if(db.serviceNameFournisseur(serviceName,user.getEmail())==true){
                    Toast.makeText(getApplicationContext(), "This service already exists", Toast.LENGTH_SHORT).show();
                }else{
                    boolean insert = db.insertServiceFournisseur(getIntent().getStringExtra("EMAIL"), (String) listView.getItemAtPosition(position));
                    if (insert = true && db.fournisseurhasservice(getIntent().getStringExtra("EMAIL"))==false) {
                        Intent j = new Intent(serviceList.this, AvailabilityFournisseur.class);
                        Toast.makeText(getApplicationContext(), "service saved: successful ", Toast.LENGTH_SHORT).show();
                        j.putExtra("EMAIL",user.getEmail() );
                        startActivity(j);
                    }
                    else if(insert = true && db.fournisseurhasservice(getIntent().getStringExtra("EMAIL"))==true){
                        Intent a = new Intent(serviceList.this, ServiceEdit.class);
                        Toast.makeText(getApplicationContext(), "service saved: successful : Avaibility kept", Toast.LENGTH_SHORT).show();
                        a.putExtra("EMAIL",user.getEmail() );
                        startActivity(a);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "service saved: fail", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}