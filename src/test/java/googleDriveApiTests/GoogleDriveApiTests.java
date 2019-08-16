package googleDriveApiTests;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import parameters.AccessCodeParameters;
import parameters.TokenParameters;
import parameters.UploadParameters;
import restClient.RestAssuredSpecifications;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class GoogleDriveApiTests extends BaseTest {


    public static String TOKEN;


    @BeforeSuite
    public void getAccessCode() {
        given().
                spec(RestAssuredSpecifications.spec()).
                parameters(AccessCodeParameters.getHeaderParameters()).
                log().
                ifValidationFails().
                when().
                get("https://accounts.google.com/o/oauth2/v2/auth").
                then().
                statusCode(200);
    }

    @BeforeSuite
    public void getToken() {
        String responseBody = given().
                spec(RestAssuredSpecifications.spec()).
                body(TokenParameters.getBody()).
                when().
                post("https://www.googleapis.com/oauth2/v4/token").jsonPath().getString("access_token");
        System.out.println(responseBody);
        TOKEN = "Bearer " + responseBody;
    }

    @Test
    public void uploadNewFile() {
        System.out.println(TOKEN);
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                body("{\n" + "  \"name\": \"MY name.txt\"}").
                log().all().
                when().
                post("https://www.googleapis.com/drive/v3/files").
                then().body("name", equalTo("MY name.txt")).and().
                statusCode(200);
    }
}
