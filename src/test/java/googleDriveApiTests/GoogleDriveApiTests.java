package googleDriveApiTests;

import models.File;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import restClient.RestAssuredSpecifications;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GoogleDriveApiTests extends BaseTest {


    @BeforeSuite
    public void settings() {
        getAccessCode();
        getToken();
        createFileAndGetId("MY name.txt");
    }

    @Test
    public void uploadNewFile() {
        File file = new File("MY name.txt");
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                body(file).
                when().
                post().
                then().
                body("name", equalTo(file.getName())).
                and().
                statusCode(200);
    }

    @Test(priority = 10)
    public void deleteFile() {
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                when().
                delete(FILE_ID).
                then().
                statusCode(204);
    }

    @Test
    public void copyFile() {
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                when().
                post(FILE_ID + "/copy").
                then().
                body("name", equalTo("MY name.txt")).
                and().
                statusCode(200);
    }

    @Test
    public void updateFile() {
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                when().
                patch(FILE_ID).
                then().
                body("id", equalTo(FILE_ID)).
                and().
                statusCode(200);
    }


    @Test
    public void getFile() {
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                when().
                get(FILE_ID).
                then().
                body("id", equalTo(FILE_ID)).
                and().
                statusCode(200);

    }

    @Test
    public void getList() {
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                when().
                get().
                then().
                statusCode(200);
    }

    @Test
    public void emptyTrash() {
        given().
                spec(RestAssuredSpecifications.spec(TOKEN)).
                when().
                delete("trash").
                then().
                statusCode(204);
    }
}
