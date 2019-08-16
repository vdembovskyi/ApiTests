package googleDriveApiTests;

import restClient.RestAssuredSpecifications;

public class BaseTest {

    RestAssuredSpecifications restAssuredSpecifications;

    public BaseTest() {
        this.restAssuredSpecifications = new RestAssuredSpecifications();
    }
}
