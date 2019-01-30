package http;

import model.Parameters;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Https connection manager.
 */
public class HttpsConnection implements HTTPUtility {
    // TODO fill methods and comments.
    HttpsConnection() {

    }

    HttpsConnection(String address, int port) {

    }

    HttpsConnection(String address, int port, ArrayList<String> parameters) {

    }

    HttpsConnection(String address, int port, ArrayList<String> parameters, Parameters parameter) {

    }

    @Override
    public void sendRequest() {
        String connectionUrl = "https://www.google.com/";
        URL url;

        try {
            url = new URL(connectionUrl);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String input;

            while ((input = reader.readLine()) != null) {
                System.out.println(input);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getResponseCode() {
        return 0;
    }

    @Override
    public String getResponseAsString() {
        return null;
    }

    @Override
    public JSONObject getResponseAsJSON() {
        return null;
    }
}
