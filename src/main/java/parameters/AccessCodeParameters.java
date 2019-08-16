package parameters;

import java.util.HashMap;

public class AccessCodeParameters {
    private static String client_id = "351043311527-m7lv5daihffh4o61o8jvh9vov16p91n5.apps.googleusercontent.com";
    private static String response_type = "code";
    private static String redirect_uri = "http://localhost";
    private static String scope = "https://www.googleapis.com/auth/drive.file";
    private static String access_type  = "offline";

    public static HashMap<String, String> getHeaderParameters() {
        HashMap<String, String> headerParameters = new HashMap<String, String>();
        headerParameters.put("client_id", client_id);
        headerParameters.put("response_type", response_type);
        headerParameters.put("redirect_uri", redirect_uri);
        headerParameters.put("scope", scope);
        headerParameters.put("access_type", access_type);
        return headerParameters;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getResponse_type() {
        return response_type;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getScope() {
        return scope;
    }

    public String getAccess_type() {
        return access_type;
    }




}
