package restClient;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.internal.TestSpecificationImpl;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;

public class RestAssuredSpecifications {

    public static RequestSpecification spec(HashMap<String, String> map) {
        RestAssuredConfig restAssuredConfig = RestAssured.config().httpClient(httpClientConfig().setParam("CONNECTION_MANAGER_TIMEOUT", 10000));
        RequestSpecification spec = new TestSpecificationImpl(
                new RequestSpecBuilder()
                        .setConfig(restAssuredConfig).
                        addParams(map).
                        build(),
        new ResponseSpecBuilder()
                .build()
        ).getRequestSpecification();

        return spec;
    }
}
