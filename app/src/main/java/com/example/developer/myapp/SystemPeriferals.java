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

    public SystemNotifications systemNotifications() {
        return new SystemNotifications(this);
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

    public class SystemNotifications {
        private final SystemPeriferals systemPeriferalsForThisSystemNotificationsModule;

        private SystemNotifications(SystemPeriferals systemPeriferalsForSystemNotificationsModule) {
            this.systemPeriferalsForThisSystemNotificationsModule = systemPeriferalsForSystemNotificationsModule;
        }

        public void sendNotificationToUserWithMessageString(String messageStringForNotification) {
            Intent intent = new Intent(ctx, HomeActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder b = new NotificationCompat.Builder(ctx);

            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setTicker("Hearty365")
                    .setContentTitle("Default notification")
                    .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                    .setContentIntent(contentIntent)
                    .setContentInfo("Info");


            NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, b.build());
        }
    }
}