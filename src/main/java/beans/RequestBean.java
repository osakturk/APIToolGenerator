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
        parameters.setCid(argument.get("cid").toString());
        parameters.setEndDate(argument.get("de").toString());
        parameters.setDuration(Long.parseLong(argument.get("du").toString()));
        parameters.setNumber(argument.get("num").toString());
        parameters.setStartDate(argument.get("da").toString());
        parameters.setUuid(argument.get("UUID").toString());
        parameters.setPhone(argument.get("p").toString());
        parameters.setCid(argument.get("dr").toString());
        return parameters;
    }
}
