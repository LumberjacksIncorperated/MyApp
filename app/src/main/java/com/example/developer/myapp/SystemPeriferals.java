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

        public void sendNotificationToUserWithMessageString(String messageStringForNotification) {
            /*Context applicationContext = systemPeriferalsForThisSystemNotificationsModule.getContextForSystemPeriferals().getApplicationContext();

            Intent intent = new Intent(applicationContext, HomeScreenActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder b = new NotificationCompat.Builder(applicationContext);

            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setTicker("Hearty365")
                    .setContentTitle("MyApp Notification")
                    .setContentText(messageStringForNotification)
                    .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                    .setContentIntent(contentIntent)
                    .setContentInfo("Info");


            NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, b.build());*/

            Context applicationContext = systemPeriferalsForThisSystemNotificationsModule.getContextForSystemPeriferals().getApplicationContext();

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(applicationContext, "notify_001");
            Intent ii = new Intent(applicationContext, HomeScreenActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext, 0, ii, 0);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("http://google.com/");
            bigText.setBigContentTitle("Today's Bible Verse");
            bigText.setSummaryText("Text in detail");

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
            mBuilder.setContentTitle("Your Title");
            mBuilder.setContentText("Your text");
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigText);

            NotificationManager mNotificationManager =
                    (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("notify_001",
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationManager.createNotificationChannel(channel);
            }

            mNotificationManager.notify(0, mBuilder.build());
        }
    }
}