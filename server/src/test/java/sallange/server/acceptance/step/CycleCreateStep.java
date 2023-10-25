package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;

public class CycleCreateStep {

    public static Long 살랑이_생성_요청하고_아이디_반환() {
        final ExtractableResponse<Response> response = 살랑이_생성_요청();
        String location = response.header("Location");
        return Long.valueOf(location.split("/")[2]);
    }

    public static ExtractableResponse<Response> 살랑이_생성_요청() {
        return RestAssured.given()
                .log().all()
                .contentType(JSON)

                .when()
                .post("/cycles")

                .then()
                .log().all()
                .extract();
    }
}
