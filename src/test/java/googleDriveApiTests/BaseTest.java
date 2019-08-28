package googleDriveApiTests;

import com.jayway.restassured.RestAssured;
import restClient.RestAssuredSpecifications;

public class BaseTest {

    RestAssuredSpecifications restAssuredSpecifications;

    public BaseTest() {
        this.restAssuredSpecifications = new RestAssuredSpecifications();
        RestAssured.baseURI = "https://www.googleapis.com/";
        RestAssured.basePath = "drive/v3/files/";
    }
}
