package googleDriveApiTests;

import org.testng.annotations.Test;
import parameters.LoginParameters;
import parameters.TokenParameters;
import parameters.UploadParameters;
import restClient.RestAssuredSpecifications;
import restClient.TestConfigData;

import static com.jayway.restassured.RestAssured.given;


public class GoogleDriveApiTests extends BaseTest {

    @Test
    public void login() {
        given().
                spec(RestAssuredSpecifications.spec(LoginParameters.getHeaderParameters())).
                log().
                ifValidationFails().
                when().
                get("https://accounts.google.com/o/oauth2/v2/auth").
                then().
                statusCode(200);
    }

    @Test
    public void getToken() {
        String responseBody = given().
                body(TokenParameters.getBody()).
                when().
                post("https://www.googleapis.com/oauth2/v4/token").jsonPath().getString("access_token");
        System.out.println(responseBody);
        TestConfigData.TOKEN = "Bearer " + responseBody;
    }

    @Test
    public void uploadNewFile() {
        given().
                spec(RestAssuredSpecifications.spec(UploadParameters.getHeaderParameters(TestConfigData.TOKEN))).
                log().
                ifValidationFails().
                when().
                post("https://www.googleapis.com/upload/drive/v3/files?uploadType=media").
                then().
                statusCode(200);
    }
}
