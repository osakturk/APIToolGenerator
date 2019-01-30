package http;

import org.json.JSONObject;

import java.io.IOException;

public interface HTTPUtility {
    /**
     * Sends request for given connection type.
     * @throws IOException
     */
    void sendRequest() throws IOException;

    /**
     * Returns response code for given connection type.
     *
     * @return Response code (int - 200 for OK)
     * @throws HttpConnectionException
     */
    int getResponseCode() throws HttpConnectionException;

    /**
     * Returns response data as string for given connection type.
     *
     * @return Response data as String.
     * @throws HttpConnectionException
     */
    String getResponseAsString() throws HttpConnectionException;

    /**
     * Returns response data as JSON Object for given connection type.
     *
     * @return Response data as JSON Object.
     * @throws HttpConnectionException
     */
    JSONObject getResponseAsJSON() throws HttpConnectionException;
}
