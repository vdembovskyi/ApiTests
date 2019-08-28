package googleDriveApiTests;

import com.jayway.restassured.RestAssured;
import parameters.UploadParameters;
import restClient.RestAssuredSpecifications;

import static com.jayway.restassured.RestAssured.given;

public class BaseTest {

    RestAssuredSpecifications restAssuredSpecifications;
    public static String TOKEN;

    public BaseTest() {
        this.restAssuredSpecifications = new RestAssuredSpecifications();
        RestAssured.baseURI = "https://www.googleapis.com/";
        RestAssured.basePath = "drive/v3/files/";
    }

    public static String createFileAndGetId(String name) {
        return given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                body("{\n" + "  \"name\": \""+name+"\"}").
                when().
                post().
                jsonPath().
                getString("id");
    }
}
