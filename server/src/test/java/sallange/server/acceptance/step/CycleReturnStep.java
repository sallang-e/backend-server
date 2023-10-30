package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;

public class CycleReturnStep {

    public static ExtractableResponse<Response> 살랑이_반납_요청(final Long userId) {
        return RestAssured.given()
                .log().all()
                .contentType(JSON)

                .when()
                .post("/return-cycle/" + userId)

                .then()
                .log().all()
                .extract();
    }
}
