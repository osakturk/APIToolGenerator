package http;

import model.Parameters;

import java.util.ArrayList;

/**
 * HTTPUtility factory class for http requests.
 */
public class HttpFactory {
    /**
     * Default connection instance generation method.
     *
     * @param type HTTPUtility HTTPUtility type
     * @return Http HTTPUtility instance
     */
    public static HTTPUtility getConnection(HttpConnectionType type) {
        switch (type) {
            case HTTP:
                return new HttpConnection();
            case HTTPS:
                return new HttpsConnection();
            default:
                return new HttpConnection();
        }
    }

    /**
     * Alternative connection instance generation method.
     *
     * @param type    HTTPUtility type
     * @param address address
     * @param port    port
     * @return Http HTTPUtility instance
     */
    public static HTTPUtility getConnection(HttpConnectionType type, String address, int port) {
        switch (type) {
            case HTTP:
                return new HttpConnection(address, port);
            case HTTPS:
                return new HttpsConnection(address, port);
            default:
                return new HttpConnection(address, port);
        }
    }

    /**
     * @param type       HTTPUtility type
     * @param address    address
     * @param port       port
     * @param parameters parameter list
     * @return Http HTTPUtility instance
     */
    public static HTTPUtility getConnection(HttpConnectionType type, String address, int port, ArrayList<String> parameters, Parameters parameter) {
        switch (type) {
            case HTTP:
                return new HttpConnection(address, port, parameters, parameter);
            case HTTPS:
                return new HttpsConnection(address, port, parameters, parameter);
            default:
                return new HttpConnection(address, port, parameters, parameter);
        }
    }
}
