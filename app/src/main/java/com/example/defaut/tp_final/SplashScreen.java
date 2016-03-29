package com.example.defaut.tp_final;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashScreen extends Activity {

    private Handler splashHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int secondsDelayed = 2;

        Runnable r = new Runnable(){
            public void run(){
                Intent brain = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(brain);
                finish();
            }
        };
        setContentView(R.layout.activity_splash_screen);
        if(isNetworkAvailable())
            splashHandler.postDelayed(r, secondsDelayed*1000);
        else {
            //Notify user they aren't connected
            Toast.makeText(getApplicationContext(), "Please, check your connection.", Toast.LENGTH_LONG).show();

            ProgressDialog progressDialog = new ProgressDialog(SplashScreen.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("You aren't connected to the internet.");
            progressDialog.show();

            Runnable rr = new Runnable(){
                public void run(){
                    //close the app
                    finish();
                }
            };

            splashHandler.postDelayed(rr, secondsDelayed*1000);


        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
