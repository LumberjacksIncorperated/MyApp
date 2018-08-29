//----------------------------------------------------------------------------------
//
// AUTHOR
// ------
// Lumberjacks Incorperated (2018)
//
//----------------------------------------------------------------------------------

package com.example.developer.myapp;

import android.os.Handler;
import android.util.Pair;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class RemoteServerAPI {
    
    private static String generateHTTPParameterQueryStringFromMappingOfParametersToValues(Map<String, String> mappingOfParametersToValues) {
        String HTTPParameterQueryString = new String();
        boolean currentlyOnTheFirstParameterInTheList = true;
        try {
            for (Map.Entry<String, String> parameterToValueMapping : mappingOfParametersToValues.entrySet()) {
                if (!currentlyOnTheFirstParameterInTheList) HTTPParameterQueryString += "&";
                String parameter = parameterToValueMapping.getKey();
                String value = parameterToValueMapping.getValue();
                HTTPParameterQueryString +=  URLEncoder.encode(parameter, "UTF-8");
                HTTPParameterQueryString += "=";
                HTTPParameterQueryString += URLEncoder.encode(value, "UTF-8");
                currentlyOnTheFirstParameterInTheList = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HTTPParameterQueryString;
    }

    private static void setURLConenctionParametersForPOSTRequest(HttpURLConnection connectionToURLForMakingPOSTRequest) {
        try {
            connectionToURLForMakingPOSTRequest.setReadTimeout(10000);
            connectionToURLForMakingPOSTRequest.setConnectTimeout(15000);
            connectionToURLForMakingPOSTRequest.setRequestMethod("GET");
            connectionToURLForMakingPOSTRequest.setDoInput(true);
            connectionToURLForMakingPOSTRequest.setDoOutput(true);
        } catch (Exception thrownException) {
            thrownException.printStackTrace();
        }
    }

    private static URL createURLToMakePOSTRequestToWithParameterQueryStringFromMappingAndStringURL(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) {
        try {
            String parameterQueryString = generateHTTPParameterQueryStringFromMappingOfParametersToValues(mappingOfParametersToValues);
            URL urlToMakePOSTRequestTo = new URL(stringUrlToMakePOSTRequestTo + "?" + parameterQueryString);
            return urlToMakePOSTRequestTo;
        } catch (Exception thrownException) {
            thrownException.printStackTrace();
        }
    }

    private static void makeHTTPPOSTRequestFromURLWithParameterToValueMapping(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) {
        try {
            URL urlToMakePOSTRequestTo = createURLToMakePOSTRequestToWithParameterQueryStringFromMappingAndStringURL(stringUrlToMakePOSTRequestTo, mappingOfParametersToValues);
            HttpURLConnection connectionToURLForMakingPOSTRequest = (HttpURLConnection) urlToMakePOSTRequestTo.openConnection();
            setURLConenctionParametersForPOSTRequest(connectionToURLForMakingPOSTRequest);

            connectionToURLForMakingPOSTRequest.connect();
            connectionToURLForMakingPOSTRequest.getInputStream();
            connectionToURLForMakingPOSTRequest.getResponseCode();

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
