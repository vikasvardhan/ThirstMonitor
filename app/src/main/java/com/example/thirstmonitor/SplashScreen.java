package com.example.thirstmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.thirstmonitor.LoginRegistration.LoginRegistrationActivity;
import com.example.thirstmonitor.MainWindow.MainActivity;

import static java.lang.Thread.sleep;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(new Runnable() {
            public void run() {

                try {
                    sleep(3000);
                    Intent splashIntent = new Intent(getApplicationContext(), LoginRegistrationActivity.class);
                    startActivity(splashIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        myThread.start();



    }}