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
import android.widget.EditText;

public class RemoteWebServerActivity extends SRActivity {
    private EditText inputMessageTextBox;

    private final int REMOTE_WEB_SERVER_UI_LAYOUT = R.layout.activity_remote_web_server;
    @Override
    protected void initialiseActivity() {
        this.setContentView(REMOTE_WEB_SERVER_UI_LAYOUT);
        inputMessageTextBox = (EditText) this.findViewById(R.id.inputMessageTextBox);
        this.initialiseSendMessageButton();
    }

    private void initialiseSendMessageButton() {
        Button sendMessageButton = (Button)this.findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(this.whenSendMessageButtonIsPressedThenSendTheMessageFromTheTextBoxToRemoteServer()); // 2 things, when it does it (toggel button clicked) and what it does (toggel wifi)
    }

    private View.OnClickListener whenSendMessageButtonIsPressedThenSendTheMessageFromTheTextBoxToRemoteServer() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSendToServer = inputMessageTextBox.getText().toString();
                RemoteServerAPI.sendMessageToRemoteServer(messageToSendToServer);
            }
        };
    }
}
