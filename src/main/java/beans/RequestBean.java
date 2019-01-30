package beans;

import http.HttpConnection;
import http.HttpConnectionType;
import http.HttpFactory;
import model.Parameters;
import org.json.JSONObject;

import java.util.ArrayList;

public class RequestBean {
    public static void start(String arg) {
        JSONObject argument = new JSONObject(arg);

        Parameters        parameter  = setParameterFromArguments(argument);
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add(argument.toString());
        HttpConnection httpConnection = (HttpConnection) HttpFactory.getConnection(HttpConnectionType.HTTP, "", 0, parameters, parameter);
        httpConnection.sendRequest();
    }

    private static Parameters setParameterFromArguments(JSONObject argument) {
        Parameters parameters = new Parameters();
        parameters.setAll(argument.get("lorem_ipsum"));
        return parameters;
    }
}
