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
import android.widget.Toast;

public class RemoteWebServerActivity extends BaseActivity {

    private EditText textBoxForInputtingUserMessage;

    private final int REMOTE_WEB_SERVER_UI_LAYOUT = R.layout.activity_remote_web_server;
    @Override
    protected void initialiseActivity() {
        this.setContentView(REMOTE_WEB_SERVER_UI_LAYOUT);
        textBoxForInputtingUserMessage = (EditText) this.findViewById(R.id.inputMessageTextBox);
        this.initialiseSendInputTextBoxMessageButton();
    }

    private void initialiseSendInputTextBoxMessageButton() {
        Button sendInputTextMessageButton = (Button)this.findViewById(R.id.sendMessageButton);
        sendInputTextMessageButton.setOnClickListener(this.whenSendInputTextMessageButtonIsPressedThenSendTheMessageFromTheTextBoxToRemoteServer());
    }

    private View.OnClickListener whenSendInputTextMessageButtonIsPressedThenSendTheMessageFromTheTextBoxToRemoteServer() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSendToServer = textBoxForInputtingUserMessage.getText().toString();
                RemoteServerAPI remoteServerAPI = RemoteServerAPI.remoteServerAPIWithContext(RemoteWebServerActivity.this.getApplicationContext());
                remoteServerAPI.sendMessageToRemoteServerWithMessageToSendAndAPIDelegate(messageToSendToServer, RemoteWebServerActivity.this.delegateWhichDisplaysAPIResponseAsToastMessage());
            }
        };
    }

    private RemoteServerAPI.RemoteServerAPIDelegate delegateWhichDisplaysAPIResponseAsToastMessage() {
        return new RemoteServerAPI.RemoteServerAPIDelegate() {
            @Override
            public void receiveResponseFromAPI(RemoteServerAPIResponse apiResponse) {
                RemoteWebServerActivity.this.showMessageToUser(apiResponse.responseAsString());
            }
        };
    }
}
