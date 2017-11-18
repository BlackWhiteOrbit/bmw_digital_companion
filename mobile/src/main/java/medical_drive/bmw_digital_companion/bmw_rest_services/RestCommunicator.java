package medical_drive.bmw_digital_companion.bmw_rest_services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class RestCommunicator {

    protected void doCommunicate(String path, RestMethod method) throws MalformedURLException {
        URL url = new URL(path);

        switch (method) {
            case GET:
                clientGet(url);
                break;
            case POST:

                break;
            case OPTIONS:
                break;
            default:
                break;
        }
    }

    public void clientGet(URL url){

        //String output = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
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
}
