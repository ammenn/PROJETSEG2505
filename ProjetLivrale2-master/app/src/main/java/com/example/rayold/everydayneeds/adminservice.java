package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rayold.everydayneeds.activities.CustomListview;
import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.Login;

public class adminservice extends AppCompatActivity {
    ListView list ;
    String[] services = {"Installation ou reparation","Nettoyage de tapis","Demenagement","Assemblement de meubles","Plomberie","Service de serrurerie","Peinture","Nettoyage de fenetre","Electricite","Service antiparasitaire"};
    Integer[] imgid = {R.drawable.im1 ,R.drawable.im2 ,R.drawable.im3 ,R.drawable.im4 ,R.drawable.im5 ,R.drawable.im6 ,R.drawable.im7 ,R.drawable.im8 ,R.drawable.im9 ,R.drawable.im10 , } ;
    public String itemstring ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur_service);

        list = (ListView) findViewById(R.id.listview) ;
        CustomListview customListview = new CustomListview(this , services , imgid) ;
        list.setAdapter(customListview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemstring = list.getItemAtPosition(position).toString();
                Intent intent = new Intent(adminservice.this, admin.class);
                intent.putExtra("A",itemstring);
                startActivity(intent);
            }
        });
    }


}
