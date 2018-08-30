//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RemoteServerAPI {


    private static String createHTTPParameterStringSectionForParameterValueMapping(Map.Entry<String, String> parameterToValueMapping) {
        String httpParameterString = "";
        String parameter = parameterToValueMapping.getKey();
        String value = parameterToValueMapping.getValue();
        try {
            httpParameterString +=  URLEncoder.encode(parameter, "UTF-8");
            httpParameterString += "=";
            httpParameterString += URLEncoder.encode(value, "UTF-8");
        } catch (Exception thrownException) {

        }
        return httpParameterString;
    }

    private static String generateHTTPParameterQueryStringFromMappingOfParametersToValues(Map<String, String> mappingOfParametersToValues) {
        String HTTPParameterQueryString = new String();
        boolean currentlyOnTheFirstParameterInTheList = true;
        for (Map.Entry<String, String> parameterToValueMapping : mappingOfParametersToValues.entrySet()) {
            if (currentlyOnTheFirstParameterInTheList) HTTPParameterQueryString += "&";
            HTTPParameterQueryString += createHTTPParameterStringSectionForParameterValueMapping(parameterToValueMapping);
            currentlyOnTheFirstParameterInTheList = false;
        }
        return HTTPParameterQueryString;
    }

    private static void setURLConenctionParametersForPOSTRequest(HttpURLConnection connectionToURLForMakingPOSTRequest) throws ProtocolException {
            connectionToURLForMakingPOSTRequest.setReadTimeout(10000);
            connectionToURLForMakingPOSTRequest.setConnectTimeout(15000);
            connectionToURLForMakingPOSTRequest.setRequestMethod("GET");
            connectionToURLForMakingPOSTRequest.setDoInput(true);
            connectionToURLForMakingPOSTRequest.setDoOutput(true);
    }

    private static URL createURLToMakePOSTRequestToWithParameterQueryStringFromMappingAndStringURL(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) throws IOException {
        String parameterQueryString = generateHTTPParameterQueryStringFromMappingOfParametersToValues(mappingOfParametersToValues);
        URL urlToMakePOSTRequestTo = new URL(stringUrlToMakePOSTRequestTo + "?" + parameterQueryString);
        return urlToMakePOSTRequestTo;
    }

    private static String sendPOSTRequestWithUrlConenctionAndReturnResponseString( HttpURLConnection connectionToURLForMakingPOSTRequest) throws IOException {
        connectionToURLForMakingPOSTRequest.connect();
        connectionToURLForMakingPOSTRequest.getInputStream();
        connectionToURLForMakingPOSTRequest.getResponseCode();
        String responseString = connectionToURLForMakingPOSTRequest.getResponseMessage();
        return responseString;
    }

    private static RemoteServerAPIResponse makeHTTPPOSTRequestFromURLWithParameterToValueMappingAndReturnAPIResponse(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) {
        RemoteServerAPIResponse apiResponse = null;
        try {
            URL urlToMakePOSTRequestTo = createURLToMakePOSTRequestToWithParameterQueryStringFromMappingAndStringURL(stringUrlToMakePOSTRequestTo, mappingOfParametersToValues);
            HttpURLConnection connectionToURLForMakingPOSTRequest = (HttpURLConnection) urlToMakePOSTRequestTo.openConnection();
            setURLConenctionParametersForPOSTRequest(connectionToURLForMakingPOSTRequest);
            String responseString = sendPOSTRequestWithUrlConenctionAndReturnResponseString(connectionToURLForMakingPOSTRequest);
            apiResponse = RemoteServerAPIResponse.responseWithMessageStringFromAPI(responseString);
        } catch (Exception thrownException) {
            thrownException.printStackTrace();
            apiResponse = RemoteServerAPIResponse.failedToCompleteAPIRequest();
        }
        return apiResponse;
    }

    private static void makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMappingAndAPIDelegate(final String stringUrlToMakePOSTRequestTo, final Map<String, String> mappingOfParametersToValues, final RemoteServerAPIDelegate apiDelegate) {
        Thread threadToSendMesasgeToServer = new Thread(new Runnable() {
            public void run() {
                RemoteServerAPIResponse apiResponse = RemoteServerAPI.makeHTTPPOSTRequestFromURLWithParameterToValueMappingAndReturnAPIResponse(stringUrlToMakePOSTRequestTo, mappingOfParametersToValues);
                apiDelegate.receiveResponseFromAPI(apiResponse);
            }
        });
        threadToSendMesasgeToServer.start();
    }

//----------------------------------------------------------------------------------
// EXPORTED FUNCTIONS
//----------------------------------------------------------------------------------
    private static final String REQUEST_URL_FOR_SENDING_MESSAGE_TO_SERVER = "http://192.168.0.146/send_message.php";
    private static final String KEY_FOR_MESSAGE_PARAMETER = "message";
    public static void sendMessageToRemoteServerWithMessageToSendAndAPIDelegate(String messageToSend, RemoteServerAPIDelegate apiDelegate) {
        HashMap parametersInRequest = new HashMap<String, String>();
        parametersInRequest.put(KEY_FOR_MESSAGE_PARAMETER, messageToSend);
        makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMappingAndAPIDelegate(REQUEST_URL_FOR_SENDING_MESSAGE_TO_SERVER, parametersInRequest, apiDelegate);
    }

    private static final String REQUEST_URL_FOR_LOGIN_TO_SERVER = "http://192.168.0.146/login.php";
    private static final String KEY_FOR_USERNAME_PARAMETER = "username";
    private static final String KEY_FOR_PASSWORD_PARAMETER = "password";
    public static void loginToRemoteServerWithUsernamePasswordAndAPIDelegate(String username, String password, RemoteServerAPIDelegate apiDelegate) {
        HashMap parametersInRequest = new HashMap<String, String>();
        parametersInRequest.put(KEY_FOR_USERNAME_PARAMETER, username);
        parametersInRequest.put(KEY_FOR_PASSWORD_PARAMETER, password);
        makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMappingAndAPIDelegate(REQUEST_URL_FOR_LOGIN_TO_SERVER, parametersInRequest, apiDelegate);
    }


//----------------------------------------------------------------------------------
// EXPORTED INTERFACES
//----------------------------------------------------------------------------------
    public interface RemoteServerAPIDelegate {
        public void receiveResponseFromAPI(RemoteServerAPIResponse apiResponse);
    }
}
