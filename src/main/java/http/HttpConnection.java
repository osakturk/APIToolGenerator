package http;

import model.Parameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/***
 * Http request connection controller class.
 */
public class HttpConnection implements HTTPUtility {
    private static final Logger            logger       = LogManager.getLogger(HttpConnection.class); // Logger instance
    private              String            address      = null; // Request URL. If null throw Exception and write into log
    private              int               port         = -1; // Request Port If <= 0 throw Exception and write into log
    private              ArrayList<String> parameters   = new ArrayList<>(); // Request Parameter list. Each parameter concatenated with '&'
    private              Parameters        parameter    = new Parameters();
    private              int               responseCode = -1; // Response code. If < 0 throw Exception and write into log
    private              String            response     = null; // Request response

    // Constructor
    HttpConnection() {
    }

    // Alternative Constructor
    HttpConnection(String address, int port) {
        this.address = address;
        this.port = port;
    }

    // Alternative Constructor
    HttpConnection(String address, int port, ArrayList<String> parameters) {
        this.address = address;
        this.port = port;
        this.parameters = parameters;
    }

    HttpConnection(String address, int port, ArrayList<String> parameters, Parameters parameter) {
        this.address = address;
        this.port = port;
        this.parameters = parameters;
        this.parameter = parameter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Add parameter for request. If key + value is in the array, skip.
     *
     * @param key   Parameter key
     * @param value Parameter value
     * @throws HttpConnectionException If parameter key is null or empty then throws exception.
     */
    public void addParameter(String key, String value) throws HttpConnectionException {
        // Check if invalid parameter key.
        if (key == null || key.equals("")) {
            logger.error("Invalid operation. Parameter key cannot be null or empty.");
            throw new HttpConnectionException("Invalid operation. Parameter key cannot be null or empty.");
        }

        // Check if parameter is already in the list.
        if (this.parameters.contains(key + "=" + value))
            logger.info("Parameter '" + key + "' is already added for value '" + value + "', skipping...");
        else {
            this.parameters.add(key + "=" + value);
        }
    }

    public void setParameters(ArrayList<String> parameters) {
        if (parameters != null) this.parameters = parameters;
        else this.parameters = new ArrayList<>();
    }

    /**
     * Sends request to given url, port and parameter information and writes response into a String object.
     */
    @Override
    public void sendRequest() {
        System.out.println("SENDING REQUEST");
        String connectionString;
        connectionString = "http://192.168.168.135:8080/api/mobile";
        System.out.println("URL : " + connectionString + ", PORT: " + this.port);

        try {
            URL               url      = new URL(connectionString);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Range", "bytes=0-99999999");
            httpConn.setRequestProperty("Authorization", "Bearer "+this.parameter.getPhone());
            System.out.println(exportReportResultsToFile(httpConn, this.parameter));
        } catch (IOException e) {
            logger.fatal("REQUEST ERROR");
            logger.fatal(e.getMessage());
            logger.fatal(e.getCause());
            e.getCause();
            e.getMessage();
            e.printStackTrace();
        }


    }

    private String exportReportResultsToFile(HttpURLConnection httpConn, Parameters parameters) throws IOException {
        System.out.println("PHONE: " + parameters.getPhone());

        StringBuilder urlParameters  = new StringBuilder("");
        boolean       firstParameter = true;
        for (String parameter : this.parameters) {
            if (!firstParameter) urlParameters.append('&');
            else firstParameter = false;
            urlParameters.append(parameter);
        }
        httpConn.setDoOutput(true);
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(httpConn.getOutputStream());
            dataOutputStream.writeBytes(urlParameters.toString());
            dataOutputStream.flush();
            dataOutputStream.close();
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String contentType   = httpConn.getContentType();
                System.out.println("Content-Type = " + contentType);
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + httpConn.getResponseCode());
            }
        } catch (IOException e) {
            e.getMessage();
            e.getCause();
            e.printStackTrace();
        } finally {
            httpConn.disconnect();
        }
        return httpConn.getResponseMessage();
    }


    /**
     * Get response code of request.
     *
     * @return Http response code
     * @throws HttpConnectionException If response code is lower than zero then response is not assigned, so throws exception.
     */
    @Override
    public int getResponseCode() throws HttpConnectionException {
        if (this.responseCode < 0) {
            logger.fatal("Invalid operation. Cannot get response code.");
            throw new HttpConnectionException("Invalid operation. Cannot get response code.");
        }
        return this.responseCode;
    }

    /**
     * Get response as String object.
     *
     * @return Response string.
     * @throws HttpConnectionException If response is null then throw exception.
     */
    @Override
    public String getResponseAsString() throws HttpConnectionException {
        if (this.response == null) {
            logger.fatal("Invalid operation. Cannot get response.");
            throw new HttpConnectionException("Invalid operation. Cannot get response.");
        }
        return this.response;
    }

    /**
     * Get response as JSON object.
     *
     * @return Response JSON.
     * @throws HttpConnectionException If response is null or response is not an valid JSON then throw exception.
     */
    @Override
    public JSONObject getResponseAsJSON() throws HttpConnectionException {
        if (this.response == null) {
            logger.fatal("Invalid operation. Cannot get response.");
            throw new HttpConnectionException("Invalid operation. Cannot get response.");
        }

        try {
            return new JSONObject(this.response);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
