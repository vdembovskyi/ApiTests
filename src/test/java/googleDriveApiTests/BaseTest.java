package googleDriveApiTests;

import com.jayway.restassured.RestAssured;
import models.File;
import models.Token;
import parameters.AccessCodeParameters;
import parameters.UploadParameters;
import restClient.RestAssuredSpecifications;
import restClient.TestConfigData;

import static com.jayway.restassured.RestAssured.given;
import static restClient.TestConfigData.CLIENT_ID;
import static restClient.TestConfigData.CLIENT_SECRET;
import static restClient.TestConfigData.REDIRECT_URI;

public class BaseTest {

    public static final String GET_TOKEN_URI = "https://www.googleapis.com/oauth2/v4/token";
    public static final String GET_ACCESS_CODE_URI = "https://accounts.google.com/o/oauth2/v2/auth";
    public static String TOKEN;
    public static String FILE_ID;


    public BaseTest() {
        //RestAssuredSpecifications restAssuredSpecifications = new RestAssuredSpecifications();
        RestAssured.baseURI = "https://www.googleapis.com/";
        RestAssured.basePath = "drive/v3/files/";
    }


    public static void getAccessCode() {
        given().
                spec(RestAssuredSpecifications.spec()).
                parameters(AccessCodeParameters.getHeaderParameters()).
                when().
                get(GET_ACCESS_CODE_URI).
                then().
                statusCode(200);
    }

    public static void getToken() {
        Token token = new Token(TestConfigData.ACCESS_CODE, "authorization_code", CLIENT_SECRET, REDIRECT_URI, CLIENT_ID);
        String responseBody = given().
                spec(RestAssuredSpecifications.spec()).
                body(token).
                when().
                post(GET_TOKEN_URI).
                jsonPath().
                getString("access_token");
        System.out.println(responseBody);
        TOKEN = "Bearer " + responseBody;
        System.out.println(token);
    }


    public void createFileAndGetId(String name) {
        File file = new File(name);
        FILE_ID = given().
                spec(RestAssuredSpecifications.
                        spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                body(file).
                when().
                post().
                jsonPath().
                getString("id");
    }
}
