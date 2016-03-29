package com.example.defaut.tp_final;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.widget.ListView;


/**
 * Created by p1406206 on 22/03/2016.
 */
public class MyAsyncTask extends AsyncTask<Object, Void, ArrayList<Seisme>> {

    int textVueRessourceiD = 0;

    MainActivity activity = null;

    ProgressDialog progressDialog;

    public MyAsyncTask(MainActivity activity){
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();


    }

    @Override
    protected ArrayList<Seisme> doInBackground(Object[] obj) {

        textVueRessourceiD = (Integer) obj[0];

        ArrayList<Seisme> listeSeisme= (ArrayList<Seisme>) obj[2];
        ArrayList<Seisme> listeSeismeTemporaire = new ArrayList<Seisme>();


        int type = (Integer) obj[3];

        String URLStr = "";

        switch (type){
            case 1:
                URLStr = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_month.geojson";
                break;

            case 2:
                URLStr = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_year.geojson";
                break;

            default:
                URLStr = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson";
                break;

        }

        String chaineTotale = new String();

        URL url = null;


        // on genere l'URL
        try {
            url = new URL(URLStr);
        }
        catch (Exception e) {
            Log.e("URL GENERATION :", e.getMessage());
        }


        // on recupere le json
        try {

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                //ensuite, on récupere le contenu du flux avec in.readLine(), tant qu'il y a des
                //données dans le flux d'entrée

                String str = "";

                while((str=in.readLine())!=null && str.length()!=0){
                    chaineTotale+=str;
                }

                in.close();   // et on ferme le flux
            }
        }
        catch (Exception e){
            Log.e("READING FILE :", e.getMessage());
        }

        // on parse le json

        JSONObject json = null;

        try {
            json = new JSONObject(chaineTotale);
        }
        catch (Exception e){
            Log.e("PARSER GENERATION :", e.getMessage());
        }

        try {


            JSONArray features  = json.getJSONArray("features");


            // on crée les instance de Seisme
            for (int i=0; i<features.length(); i++) {

                JSONObject jsonObj  = features.getJSONObject(i);

                JSONObject properties  = jsonObj.getJSONObject("properties");

                Seisme seisme = new Seisme();

                seisme.setMag(Float.parseFloat(properties.optString("mag").toString()));
                seisme.setPlace(properties.optString("place"));
                seisme.setTime(properties.optLong("time"));
                seisme.setUpdated(Long.parseLong(properties.optString("updated").toString()));
                seisme.setUrl(properties.optString("url"));
                seisme.setDetail(properties.optString("detail"));
                seisme.setTsunami(Integer.parseInt(properties.optString("tsunami").toString()));


                JSONObject geometry  = jsonObj.getJSONObject("geometry");

                seisme.setLocation(geometry.optString("coordinates").toString());

                listeSeismeTemporaire.add(seisme);
            }


            listeSeisme.clear();
            for (int i=0; i<listeSeismeTemporaire.size(); i++ ){
                listeSeisme.add(listeSeismeTemporaire.get(i));
            }


        }
        catch (Exception e){
            Log.e("PARSER USE :", e.getMessage());
        }



        return listeSeisme;
    }



    @Override
    protected void onPostExecute(ArrayList<Seisme> o) {
        super.onPostExecute(o);

        // on crée la vue
        ListView listview = (ListView) activity.findViewById(textVueRessourceiD);

        final MyAdapter adapter= new MyAdapter(activity, android.R.layout.simple_list_item_1, o);
        listview.setAdapter(adapter);

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
