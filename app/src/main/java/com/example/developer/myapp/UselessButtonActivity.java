//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import android.view.View;
import android.widget.Button;

public class UselessButtonActivity extends BaseActivity {

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
