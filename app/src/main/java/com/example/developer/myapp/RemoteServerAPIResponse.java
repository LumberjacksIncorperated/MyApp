//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

public class RemoteServerAPIResponse {

    private final String responseAsString;

    private RemoteServerAPIResponse(String responseAsString) {
        this.responseAsString = responseAsString;
    }

    public String responseAsString() {
        return responseAsString;
    }

    public static RemoteServerAPIResponse failedToCompleteAPIRequest() {
        return new RemoteServerAPIResponse("Failed to connect to API");
    }

    public static RemoteServerAPIResponse responseWithMessageStringFromAPI(String messageStringFromAPI) {
        return new RemoteServerAPIResponse(messageStringFromAPI);
    }
}

