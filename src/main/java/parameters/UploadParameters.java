package parameters;

import java.util.HashMap;

public class UploadParameters {
    private static String contentType = "application/json";
    private static String contentLength = "0";

    public static HashMap<String, String> getHeaderParameters(String token) {
        HashMap<String, String> headerParameters = new HashMap<String, String>();
        headerParameters.put("Content-type", contentType);
        headerParameters.put("Authorization", token);
        //headerParameters.put("Content-length", contentLength);
        return headerParameters;
    }

    public static String getContentType() {
        return contentType;
    }

    public static String getContentLength() {
        return contentLength;
    }
}
