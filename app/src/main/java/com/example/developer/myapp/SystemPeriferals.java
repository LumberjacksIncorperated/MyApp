//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationChannel;

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

        private final int NO_FLAG = 0;
        private final static int PENDING_INTENT_REQUEST_CODE_FOR_PUSH_NOTIFICATION = 0;
        public void sendNotificationToUserWithMessageString(String messageStringForNotification) {

            // Get The Application Context
            Context applicationContext = systemPeriferalsForThisSystemNotificationsModule.getContextForSystemPeriferals().getApplicationContext();

            // Create pending intent
            Intent intentToGoToTheHomeScreenActivity = new Intent(applicationContext, HomeScreenActivity.class);
            PendingIntent pendingIntentToGoToTheHomeScreenActivity = PendingIntent.getActivity(applicationContext, PENDING_INTENT_REQUEST_CODE_FOR_PUSH_NOTIFICATION, intentToGoToTheHomeScreenActivity, NO_FLAG);

            // Create big text
            String pushNotificationTitle = "MyApp Notification";
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText(messageStringForNotification);
            bigText.setBigContentTitle(pushNotificationTitle);
            bigText.setSummaryText(messageStringForNotification);

            // Set variables in push notification builder
            NotificationCompat.Builder pushNotificationBuilder = new NotificationCompat.Builder(applicationContext, "notify_001");
            pushNotificationBuilder.setContentIntent(pendingIntentToGoToTheHomeScreenActivity);
            pushNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
            pushNotificationBuilder.setContentTitle(pushNotificationTitle);
            pushNotificationBuilder.setContentText(messageStringForNotification);
            pushNotificationBuilder.setPriority(Notification.PRIORITY_MAX);
            pushNotificationBuilder.setStyle(bigText);

            // Use manager for the final steps (needs builder)
            NotificationManager pushNotificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("notify_001",
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                pushNotificationManager.createNotificationChannel(channel);
            }
            pushNotificationManager.notify(0, pushNotificationBuilder.build());
        }
    }
}