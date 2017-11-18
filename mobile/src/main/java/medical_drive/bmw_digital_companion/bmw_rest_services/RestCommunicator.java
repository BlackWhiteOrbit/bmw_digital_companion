package medical_drive.bmw_digital_companion.bmw_rest_services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class RestCommunicator {

    public static final int ACCEPTED_RESPONSE_CODE = 200;

    protected void doCommunicate(String path, RestMethod method) throws MalformedURLException {
        URL url = new URL(path);

        switch (method) {
            case GET:
                clientGet(url, method);
                break;
            case POST:
                //TODO
                clientPost(url);
                break;
            case OPTIONS:
                //TODO
                break;
            default:
                break;
        }
    }

    public void clientGet(URL url, RestMethod method){

        //String output = null;

        System.out.println(url);

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(method.name());
            connection.setRequestProperty(RestServiceConstants.API_HEADER_KEY, RestServiceConstants.API_HEADER_VALUE);
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != ACCEPTED_RESPONSE_CODE) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            BufferedReader buffReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = buffReader.readLine()) != null) {
                System.out.println(output);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return output;
    }

    //TODO
    public void clientPost(URL url) {
        try {

            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":1,\"firstName\":\"Liam\",\"age\":,\"lastName\":\"Marco\"}";

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
            (httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:\n");
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
            }

            httpConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
