package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sallange.server.api.request.CycleStatusUpdateRequest;

import static io.restassured.http.ContentType.JSON;

public class CycleStatusUpdateStep {

    public static ExtractableResponse<Response> 살랑이_상태_변경_요청(CycleStatusUpdateRequest request) {
        return RestAssured.given()
                .log().all()
                .body(request)
                .contentType(JSON)

                .when()
                .patch("/admin/cycles/change-status")

                .then()
                .log().all()
                .extract();
    }
}
