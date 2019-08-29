package restClient;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.internal.TestSpecificationImpl;
import com.jayway.restassured.specification.RequestSpecification;
import parameters.UploadParameters;

import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;

public class RestAssuredSpecifications {

    public static RestAssuredConfig setConfigParameters() {
        return RestAssured.
                config().
                httpClient(httpClientConfig().
                        setParam("CONNECTION_MANAGER_TIMEOUT", 300000));
    }

    public static RequestSpecification spec() {
        return new TestSpecificationImpl(
                new RequestSpecBuilder().
                        setConfig(setConfigParameters()).
                        build(),
                new ResponseSpecBuilder().
                        build()).
                getRequestSpecification().
                log().
                all();
    }

    public static RequestSpecification spec(String TOKEN) {
        return new TestSpecificationImpl(
                new RequestSpecBuilder().
                        setConfig(setConfigParameters()).
                        build().headers(UploadParameters.getHeaderParameters(TOKEN)),
                new ResponseSpecBuilder().
                        build()).
                getRequestSpecification().
                log().
                all();
    }
}
