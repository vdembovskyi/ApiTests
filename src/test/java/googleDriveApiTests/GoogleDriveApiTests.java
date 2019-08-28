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

    @BeforeSuite
    public void getAccessCode() {
        given().
                spec(RestAssuredSpecifications.spec()).
                parameters(AccessCodeParameters.getHeaderParameters()).
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
                post("https://www.googleapis.com/oauth2/v4/token").
                jsonPath().
                getString("access_token");
        System.out.println(responseBody);
        TOKEN = "Bearer " + responseBody;
    }

    @Test
    public void uploadNewFile() {
        String fileId = createFileAndGetId("MY name.txt");
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                body("{\n" + "  \"name\": \"MY name.txt\"}").
                when().
                post().
                then().
                body("name", equalTo("MY name.txt")).
                and().
                statusCode(200);
    }

    @Test
    public void deleteFile() {
        String fileId = createFileAndGetId("MY name.txt");
        //delete file
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                when().
                delete(fileId).
                then().
                statusCode(204);
    }

    @Test
    public void copyFile() {
        String fileId = createFileAndGetId("MY name.txt");
        //copy file
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                when().
                post(fileId + "/copy").
                then().
                body("name", equalTo("MY name.txt")).
                and().
                statusCode(200);
    }

    @Test
    public void updateFile() {
        String fileId = createFileAndGetId("MY name.txt");
        //update file
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                when().
                patch(fileId).
                then().
                body("id", equalTo(fileId)).
                and().
                statusCode(200);
    }


    @Test
    public void getFile() {
        String fileId = createFileAndGetId("MY name.txt");
        //get file
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                when().
                get(fileId).
                then().
                body("id", equalTo(fileId)).
                and().
                statusCode(200);
    }

    @Test
    public void getList() {
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                when().
                get().
                then().
                statusCode(200);
    }

    @Test
    public void emptyTrash() {
        given().
                spec(RestAssuredSpecifications.spec().
                        headers(UploadParameters.getHeaderParameters(TOKEN))).
                when().
                delete("trash").
                then().
                statusCode(204);
    }
}
