package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;

public class CycleReturnStep {

    public static ExtractableResponse<Response> 살랑이_반납_요청(final String accessToken) {
        return RestAssured.given()
                .log().all()
                .auth().preemptive()
                .oauth2(accessToken)
                .contentType(JSON)

                .when()
                .post("/cycles/return-cycle")

                .then()
                .log().all()
                .extract();
    }
}
