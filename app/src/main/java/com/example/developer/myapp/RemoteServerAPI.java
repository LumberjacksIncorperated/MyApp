package com.example.developer.myapp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Dictionary;

import javax.net.ssl.HttpsURLConnection;

public class RemoteServerAPI {

    private void makeHTTPPOSTRequestFromURLWithParameterToValueMapping(String stringUrlToMakePOSTRequestTo, Dictionary<String, String> mappingOfParametersToValues) {
        try {

            URL urlToMakePOSTRequestTo = new URL(stringUrlToMakePOSTRequestTo);
            HttpURLConnection connectionToURLForMakingPOSTRequest = (HttpURLConnection) urlToMakePOSTRequestTo.openConnection();
            //conn.setReadTimeout(10000);
            //conn.setConnectTimeout(15000);
            connectionToURLForMakingPOSTRequest.setRequestMethod("POST");
            //connectionToURLForMakingPOSTRequest.setDoInput(false);
            //connectionToURLForMakingPOSTRequest.setDoOutput(false);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firstParam", paramValue1));
            params.add(new BasicNameValuePair("secondParam", paramValue2));
            params.add(new BasicNameValuePair("thirdParam", paramValue3));

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToRemoteServer(String messageToSend) {

    }

}
