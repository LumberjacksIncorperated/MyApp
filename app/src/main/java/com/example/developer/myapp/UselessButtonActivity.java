package com.example.developer.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UselessButtonActivity extends SRActivity {

    private final int USELESS_BUTTON_SCREEN_UI_LAYOUT = R.layout.activity_wifi_settings;
    @Override
    protected void initialiseActivity() {
        this.setContentView(USELESS_BUTTON_SCREEN_UI_LAYOUT);
        this.initialiseUselessButton();
    }

    private void initialiseUselessButton() {
        Button uselessButton = (Button)this.findViewById(R.id.wifiToggelButton);
        uselessButton.setText("Useless Button");
        uselessButton.setOnClickListener(this.whenUselessButtonIsClickedThenDoSomethingUseless());
    }

    private View.OnClickListener whenUselessButtonIsClickedThenDoSomethingUseless() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UselessButtonActivity.this.moveBackToThePreviousActivity();
            }
        };
    }

    private void moveBackToThePreviousActivity() {
        this.finish();
    }
}
