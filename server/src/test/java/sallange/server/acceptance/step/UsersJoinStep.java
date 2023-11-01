package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sallange.server.auth.api.request.UserJoinRequest;

import static io.restassured.http.ContentType.JSON;

public class UsersJoinStep {

    public static String 회원_가입_요청하고_액세스_토큰_반환(final UserJoinRequest request) {
        final ExtractableResponse<Response> response = 회원_가입_요청(request);
        return response.jsonPath().getString("accessToken");
    }

    public static ExtractableResponse<Response> 회원_가입_요청(final UserJoinRequest request) {
        return RestAssured.given()
                .log().all()
                .contentType(JSON)
                .body(request)

                .when()
                .post("/test/users")

                .then()
                .log().all()
                .extract();
    }
}
