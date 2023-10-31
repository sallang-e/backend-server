package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sallange.server.api.request.CycleStatusQueryRequest;

import static io.restassured.http.ContentType.JSON;

public class CycleStatusQueryStep {

    public static String 살랑이_상태_조회_요청하고_상태_반환(final Long cycleId) {
        final ExtractableResponse<Response> response = 살랑이_상태_조회_요청(cycleId);
        return response.jsonPath().getString("status");
    }

    public static ExtractableResponse<Response> 살랑이_상태_조회_요청(final Long cycleId) {
        return RestAssured.given()
                .log().all()
                .body(new CycleStatusQueryRequest(cycleId))
                .contentType(JSON)

                .when()
                .get("/admin/cycles/status")

                .then()
                .log().all()
                .extract();
    }
}
