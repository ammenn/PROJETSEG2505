package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;

import java.util.ArrayList;

public class ServiceList2 extends AppCompatActivity {

    DatabaseHelper db;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list2);
        final ListView listView = (ListView) findViewById(R.id.serviceList);
        db = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getService();
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no service available. Admin didn't add any yet", Toast.LENGTH_LONG).show();
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

                Intent j = new Intent(ServiceList2.this, FournisseurListForEachService.class);
                j.putExtra("SERVICE",(String) listView.getItemAtPosition(position));
                startActivity(j);
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
