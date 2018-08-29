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

    public static URL createURLToMakePOSTRequestToWithParameterQueryStringFromMappingAndStringURL(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) throws IOException {
        String parameterQueryString = generateHTTPParameterQueryStringFromMappingOfParametersToValues(mappingOfParametersToValues);
        URL urlToMakePOSTRequestTo = new URL(stringUrlToMakePOSTRequestTo + "?" + parameterQueryString);
        return urlToMakePOSTRequestTo;
    }

    private static void sendPOSTRequestWithUrlConenction( HttpURLConnection connectionToURLForMakingPOSTRequest) throws IOException {
        connectionToURLForMakingPOSTRequest.connect();
        connectionToURLForMakingPOSTRequest.getInputStream();
        connectionToURLForMakingPOSTRequest.getResponseCode();
    }

    private static void makeHTTPPOSTRequestFromURLWithParameterToValueMapping(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) {
        try {
            URL urlToMakePOSTRequestTo = createURLToMakePOSTRequestToWithParameterQueryStringFromMappingAndStringURL(stringUrlToMakePOSTRequestTo, mappingOfParametersToValues);
            HttpURLConnection connectionToURLForMakingPOSTRequest = (HttpURLConnection) urlToMakePOSTRequestTo.openConnection();
            setURLConenctionParametersForPOSTRequest(connectionToURLForMakingPOSTRequest);
            sendPOSTRequestWithUrlConenction(connectionToURLForMakingPOSTRequest);
        } catch (Exception thrownException) {
            thrownException.printStackTrace();
        }
    }

    private static void makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMapping(final String stringUrlToMakePOSTRequestTo, final Map<String, String> mappingOfParametersToValues) {
        Thread threadToSendMesasgeToServer = new Thread(new Runnable() {
            public void run() {
                RemoteServerAPI.makeHTTPPOSTRequestFromURLWithParameterToValueMapping(stringUrlToMakePOSTRequestTo, mappingOfParametersToValues);
            }
        });
        threadToSendMesasgeToServer.start();
    }

    private static final String REQUEST_URL_FOR_SENDING_MESSAGE_TO_SERVER = "http://192.168.0.146/send_message.php";
    private static final String KEY_FOR_MESSAGE_PARAMETER = "message";
    public static void sendMessageToRemoteServer(String messageToSend) {
        HashMap parametersInRequest = new HashMap<String, String>();
        parametersInRequest.put(KEY_FOR_MESSAGE_PARAMETER, messageToSend);
        makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMapping(REQUEST_URL_FOR_SENDING_MESSAGE_TO_SERVER, parametersInRequest);
    }
}
