package com.example.defaut.tp_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends Activity {


    ArrayList<Seisme> listeSeisme = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        listeSeisme = new  ArrayList<Seisme>();
        Object[]liste = {R.id.liste, this, listeSeisme, 0};



        new MyAsyncTask().execute(liste);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView maListeVue = (ListView) findViewById(R.id.liste);

        maListeVue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //(Seisme) maListeVue.getItemAtPosition(position);



                Intent intent = new Intent(MainActivity.this, MapsActivitySingle.class);

                Seisme seisme = (Seisme) maListeVue.getItemAtPosition(position);


                intent.putExtra("mag", Float.toString(seisme.getMag()));
                intent.putExtra("place", seisme.getPlace());
                intent.putExtra("time", seisme.getTime());

                intent.putExtra("longitude", seisme.getLongitude().toString());
                intent.putExtra("latitude", seisme.getLatitude().toString());
                intent.putExtra("depth", seisme.getDepth().toString());

                startActivity(intent);

            }
        });

        final Button monBoutonShowAll = (Button) findViewById(R.id.buttonShowAll);

        monBoutonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> listeMag = new ArrayList<String>();
                ArrayList<String> listePlace = new ArrayList<String>();
                ArrayList<String> listeTime = new ArrayList<String>();

                ArrayList<String> listeLong = new ArrayList<String>();
                ArrayList<String> listeLat = new ArrayList<String>();
                ArrayList<String> listeDeph = new ArrayList<String>();


                for (int i=0; i< listeSeisme.size(); i++){
                    Seisme seisme = listeSeisme.get(i);

                    listeMag.add(seisme.getMag().toString());
                    listePlace.add(seisme.getPlace().toString());
                    listeTime.add(seisme.getTime().toString());

                    listeLong.add(seisme.getLongitude().toString());
                    listeLat.add(seisme.getLatitude().toString());
                    listeDeph.add(seisme.getDepth().toString());
                }

                Intent intent = new Intent(MainActivity.this, MapsActivityAll.class);

                intent.putExtra("size", Integer.toString(listeSeisme.size()));

                intent.putExtra("mag", listeMag);
                intent.putExtra("place", listePlace);
                intent.putExtra("time", listeTime);

                intent.putExtra("longitude", listeLong);
                intent.putExtra("latitude", listeLat);
                intent.putExtra("depth", listeDeph);

                startActivity(intent);
            }
        });

        final Button monBoutonJour = (Button) findViewById(R.id.btDay);

        monBoutonJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object[]liste = {R.id.liste, MainActivity.this, listeSeisme, 0};



                new MyAsyncTask().execute(liste);

            }
        });



        final Button monBoutonMois = (Button) findViewById(R.id.btMonth);


        monBoutonMois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object[]liste = {R.id.liste, MainActivity.this, listeSeisme, 1};



                new MyAsyncTask().execute(liste);

            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
