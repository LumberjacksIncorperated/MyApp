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
        StringBuilder result = new StringBuilder();
        boolean currentlyOnTheFirstParameterInTheList = true;

        try {

            for (Map.Entry<String, String> parameterToValueMapping : mappingOfParametersToValues.entrySet()) {
                String parameter = parameterToValueMapping.getKey();
                String value = parameterToValueMapping.getValue();

                if (!currentlyOnTheFirstParameterInTheList) result.append("&");

                result.append(URLEncoder.encode(parameter, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value, "UTF-8"));

                currentlyOnTheFirstParameterInTheList = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private static void makeHTTPPOSTRequestFromURLWithParameterToValueMapping(String stringUrlToMakePOSTRequestTo, Map<String, String> mappingOfParametersToValues) {
        try {

            URL urlToMakePOSTRequestTo = new URL(stringUrlToMakePOSTRequestTo);
            HttpURLConnection connectionToURLForMakingPOSTRequest = (HttpURLConnection) urlToMakePOSTRequestTo.openConnection();
            connectionToURLForMakingPOSTRequest.setReadTimeout(10000);
            connectionToURLForMakingPOSTRequest.setConnectTimeout(15000);
            connectionToURLForMakingPOSTRequest.setRequestMethod("POST");
            connectionToURLForMakingPOSTRequest.setDoInput(true);
            connectionToURLForMakingPOSTRequest.setDoOutput(true);

            OutputStream connectionOutputStream = connectionToURLForMakingPOSTRequest.getOutputStream();
            BufferedWriter bufferedWriterForConnectionOutputStream = new BufferedWriter(
                    new OutputStreamWriter(connectionOutputStream, "UTF-8"));
            String parameterQueryString = generateHTTPParameterQueryStringFromMappingOfParametersToValues(mappingOfParametersToValues);
            bufferedWriterForConnectionOutputStream.write(parameterQueryString);
            bufferedWriterForConnectionOutputStream.flush();
            bufferedWriterForConnectionOutputStream.close();
            connectionOutputStream.close();

            connectionToURLForMakingPOSTRequest.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMapping(final String stringUrlToMakePOSTRequestTo, final Map<String, String> mappingOfParametersToValues) {
        Thread threadToGoFoodASAP = new Thread(new Runnable() {
            public void run() {
                RemoteServerAPI.makeHTTPPOSTRequestFromURLWithParameterToValueMapping(stringUrlToMakePOSTRequestTo, mappingOfParametersToValues);
            }
        });
        threadToGoFoodASAP.start();
    }




    private static final String REQUEST_URL_FOR_SENDING_MESSAGE_TO_SERVER = "http://192.168.0.146/send_message.php";
    private static final String KEY_FOR_MESSAGE_PARAMETER = "message";

    public static void sendMessageToRemoteServer(String messageToSend) {
        HashMap parametersInRequest = new HashMap<String, String>();
        parametersInRequest.put(KEY_FOR_MESSAGE_PARAMETER, messageToSend);
        makeAsyncronousHTTPPOSTRequestFromURLWithParameterToValueMapping(REQUEST_URL_FOR_SENDING_MESSAGE_TO_SERVER, parametersInRequest);
    }

}
