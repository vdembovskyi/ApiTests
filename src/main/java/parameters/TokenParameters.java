package parameters;

import restClient.TestConfigData;

import static restClient.TestConfigData.*;

public class TokenParameters {

    static String body = "{\n" +
            "\"code\" : \"" + TestConfigData.ACCESS_CODE + "\",\n" +
            "\"client_id\" : \"" + CLIENT_ID + "\",\n" +
            "\"client_secret\" : \"" + CLIENT_SECRET + "\",\n" +
            "\"grant_type\" : \"authorization_code\",\n" +
            "\"redirect_uri\" : \"" + REDIRECT_URI + "\"\n" +
            "}";

    public static String getBody() {
        return body;
    }
}
