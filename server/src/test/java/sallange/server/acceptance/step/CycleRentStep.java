package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sallange.server.api.request.RentRequest;

import static io.restassured.http.ContentType.JSON;

public class CycleRentStep {

    public static ExtractableResponse<Response> 살랑이_대여_요청(final Long userId, RentRequest request) {
        return RestAssured.given()
                .log().all()
                .contentType(JSON)
                .body(request)

                .when()
                .post("/rent/" + userId)

                .then()
                .log().all()
                .extract();
    }
}
