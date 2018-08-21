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

public class SystemPeriferals extends Object {

    private final Context theContextForTheSystemPeriferals;

    private Context getContextForSystemPeriferals() {
        return this.theContextForTheSystemPeriferals;
    }

    private SystemPeriferals(Context theContextToContextualisedSystemPeriferalsAndSuch) {
        super();
        this.theContextForTheSystemPeriferals = theContextToContextualisedSystemPeriferalsAndSuch;
    }

    public static SystemPeriferals systemPeriferalsWithContext(Context theContextInQuestion) {
        SystemPeriferals newSystemPeriferals = new SystemPeriferals(theContextInQuestion);
        return newSystemPeriferals;
    }

    public Wifi wifi() {
        return new Wifi(this);
    }

    enum WifiState {
        WIFI_DISABLED, WIFI_ENABLED
    }

    public class Wifi {

        private final SystemPeriferals systemPeriferalsForThisWifiModule;

        private Wifi(SystemPeriferals systemPeriferalsForWifiModule) {
            this.systemPeriferalsForThisWifiModule = systemPeriferalsForWifiModule;
        }

        private WifiManager getWifiManagerForThisSystem() {
            Context applicationContext = systemPeriferalsForThisWifiModule.getContextForSystemPeriferals().getApplicationContext();
            WifiManager wifiManager = (WifiManager) applicationContext.getSystemService(Context.WIFI_SERVICE);
            return wifiManager;
        }

        public WifiState currentWifiState() {
            WifiManager wifiManager = this.getWifiManagerForThisSystem();
            boolean wifiIsCurrentlyEnabled = wifiManager.isWifiEnabled();
            WifiState currentWifiState = wifiIsCurrentlyEnabled ? WifiState.WIFI_ENABLED : WifiState.WIFI_DISABLED;
            return currentWifiState;
        }

        public void setWifiState(WifiState theWifiStateToSet) {
            WifiManager wifiManager = this.getWifiManagerForThisSystem();
            boolean theWifiShouldBeEnabled = (theWifiStateToSet == WifiState.WIFI_ENABLED);
            wifiManager.setWifiEnabled(theWifiShouldBeEnabled);
        }
    }
}