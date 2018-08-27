//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends SRActivity {

    private final int HOME_SCREEN_MAIN_UI_LAYOUT = R.layout.activity_main;
    @Override
    protected void initialiseActivity() {
        this.setContentView(HOME_SCREEN_MAIN_UI_LAYOUT);
        this.initialiseHomeButtons();
    }

    private void initialiseHomeButtons() {
        this.initialiseG1Button();
        this.initialiseG2Button();
<<<<<<< HEAD
        this.initialiseG4Button();
=======
        this.initialiseG3Button();
>>>>>>> a749bc2594ba3edbf3f869296da8a457b330f83c
    }

    private void initialiseG1Button() {
        Button g1Button = (Button)this.findViewById(R.id.g1Button);
        g1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeScreenActivity.this.moveToTheWifiSettingsActivity();
            }
        });
    }

    private void initialiseG2Button() {
        Button g2Button = (Button)this.findViewById(R.id.g2Button);
        g2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeScreenActivity.this.moveToTheRemoteWebServerActivity();
            }
        });
    }

<<<<<<< HEAD
    private void initialiseG4Button() {
        Button g2Button = (Button)this.findViewById(R.id.g4Button);
        g2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeScreenActivity.this.moveToTheEverythingButtonActivity();
=======
    private void initialiseG3Button() {
        Button g2Button = (Button)this.findViewById(R.id.g3Button);
        g2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeScreenActivity.this.moveToTheUselessButtonActivity();
>>>>>>> a749bc2594ba3edbf3f869296da8a457b330f83c
            }
        });
    }

<<<<<<< HEAD

=======
>>>>>>> a749bc2594ba3edbf3f869296da8a457b330f83c
    private void moveToTheWifiSettingsActivity() {
        Context applicationContext = this.getApplicationContext();
        Intent intentToMoveToTheWifiSettingsActivity = new Intent(applicationContext, WifiSettingsActivity.class);
        this.startActivity(intentToMoveToTheWifiSettingsActivity);
    }

    private void moveToTheRemoteWebServerActivity() {
        Context applicationContext = this.getApplicationContext();
        Intent intentToMoveToTheRemoteWebServerActivity = new Intent(applicationContext, RemoteWebServerActivity.class);
        this.startActivity(intentToMoveToTheRemoteWebServerActivity);
    }

<<<<<<< HEAD
    private void moveToTheEverythingButtonActivity() {
        Context applicationContext = this.getApplicationContext();
        Intent intentToMoveToTheEverythingButtonActivity = new Intent(applicationContext, EverythingButtonActivity.class);
        this.startActivity(intentToMoveToTheEverythingButtonActivity);
=======
    private void moveToTheUselessButtonActivity() {
        Context applicationContext = this.getApplicationContext();
        Intent intentToMoveToTheUselessButtonActivity = new Intent(applicationContext, UselessButtonActivity.class);
        this.startActivity(intentToMoveToTheUselessButtonActivity);
>>>>>>> a749bc2594ba3edbf3f869296da8a457b330f83c
    }
}
