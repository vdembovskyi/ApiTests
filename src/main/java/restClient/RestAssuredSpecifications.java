package restClient;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.internal.TestSpecificationImpl;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;

public class RestAssuredSpecifications {

    public static RequestSpecification spec() {
        RestAssuredConfig restAssuredConfig = RestAssured.config().httpClient(httpClientConfig().setParam("CONNECTION_MANAGER_TIMEOUT", 300000));
        RequestSpecification s = new TestSpecificationImpl(new RequestSpecBuilder().
                setConfig(restAssuredConfig).
                build(), new ResponseSpecBuilder().
                build()).
                getRequestSpecification();

        return s;
    }
}
