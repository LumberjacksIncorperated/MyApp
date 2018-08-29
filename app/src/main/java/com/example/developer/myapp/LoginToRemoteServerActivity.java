//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

public class LoginToRemoteServerActivity extends BaseActivity {

    private final int LOGIN_SCREEN_UI_LAYOUT = R.layout.login_activity_screen;
    @Override
    protected void initialiseActivity() {
        this.setContentView(LOGIN_SCREEN_UI_LAYOUT);
        this.initialiseScreenButtons();
    }

    private void initialiseScreenButtons() {

    }
}
