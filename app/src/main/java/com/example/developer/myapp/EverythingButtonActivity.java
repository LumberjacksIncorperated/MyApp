package com.example.developer.myapp;

import android.view.View;
import android.widget.Button;

public class EverythingButtonActivity extends SRActivity {

    private final int EVERYTHING_BUTTON_SCREEN_UI_LAYOUT = R.layout.activity_wifi_settings;
    @Override
    protected void initialiseActivity() {
        this.setContentView(EVERYTHING_BUTTON_SCREEN_UI_LAYOUT);
        this.initialiseUselessButton();
    }

    private void initialiseUselessButton() {
        Button uselessButton = (Button)this.findViewById(R.id.wifiToggelButton);
        uselessButton.setText("Everything Button");
        uselessButton.setOnClickListener(this.whenEverythingButtonIsClickedThenDoEverything());
    }

    private View.OnClickListener whenEverythingButtonIsClickedThenDoEverything() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EverythingButtonActivity.this.doEverything();
            }
        };
    }

    private void doEverything() {
        systemPeriferals().systemNotifications().sendNotificationToUserWithMessageString("If you are seeing this, everyting has been done.");
    }

}
