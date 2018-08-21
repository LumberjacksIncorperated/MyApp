//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class WifiSettingsActivity extends SRActivity {

    private final int WIFI_SETTINGS_UI_LAYOUT = R.layout.activity_wifi_settings;
    @Override
    protected void initialiseActivity() {
        this.setContentView(WIFI_SETTINGS_UI_LAYOUT);
        this.initialiseWifiToggleButton();
    }

    private void initialiseWifiToggleButton() {
        Button wifiToggelButton = (Button)this.findViewById(R.id.wifiToggelButton);
        wifiToggelButton.setOnClickListener(this.whenWifiToggelButtonIsClickedThenToggleWifi()); // 2 things, when it does it (toggel button clicked) and what it does (toggel wifi)
    }

    private View.OnClickListener whenWifiToggelButtonIsClickedThenToggleWifi() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiSettingsActivity.this.toggelThisDevicesWifi();
            }
        };
    }

    private void toggelThisDevicesWifi() {
        SystemPeriferals.WifiState currentStateOfWifi = systemPeriferals().wifi().currentWifiState();
        SystemPeriferals.WifiState theNewWifiState = this.theWifiStateThatIsNotThe(currentStateOfWifi); // come back to this name
        this.showMessageToUserAboutChangingToWifiState(theNewWifiState);
        systemPeriferals().wifi().setWifiState(theNewWifiState);
    }

    private SystemPeriferals.WifiState theWifiStateThatIsNotThe(SystemPeriferals.WifiState currentStateOfWifi) {
        return (currentStateOfWifi == SystemPeriferals.WifiState.WIFI_ENABLED)
                ?SystemPeriferals.WifiState.WIFI_DISABLED
                :SystemPeriferals.WifiState.WIFI_ENABLED;
    }

    private void showMessageToUserAboutChangingToWifiState(SystemPeriferals.WifiState theWifiStateInQuestion) {
        String stringDescriptionOfTheWifiStateThatWeHaseNotBeenSwitchedToNow = this.stringDescriptionOfWifiState(theWifiStateInQuestion);
        WifiSettingsActivity.this.showMessageToUser("Switching the Wifi " + stringDescriptionOfTheWifiStateThatWeHaseNotBeenSwitchedToNow);
    }

    private String stringDescriptionOfWifiState(SystemPeriferals.WifiState theWifiStateInQuestion) {
        String stringDescriptionOfTheWifiStateAsOnOrOff = (theWifiStateInQuestion == SystemPeriferals.WifiState.WIFI_ENABLED)
                ?"On"
                :"Off";
        return stringDescriptionOfTheWifiStateAsOnOrOff;
    }
}