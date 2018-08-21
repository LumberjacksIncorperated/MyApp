//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SRActivity extends AppCompatActivity {

    protected void initialiseActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initialiseActivity();
    }

    protected void showMessageToUser(String messageToShowToUser) {
        Context applicationContext = this.getApplicationContext();
        Toast theToastMessageToShowToUser = Toast.makeText(applicationContext, messageToShowToUser, Toast.LENGTH_LONG);
        theToastMessageToShowToUser.show();
    }

    protected SystemPeriferals systemPeriferals() {
        Context someContext = this.getApplicationContext();
        SystemPeriferals theSystemPeriferals = SystemPeriferals.systemPeriferalsWithContext(someContext);
        return theSystemPeriferals;
    }
}
