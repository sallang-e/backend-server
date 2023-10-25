package sallange.server.acceptance.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sallange.server.api.request.UserJoinRequest;

import static io.restassured.http.ContentType.JSON;

public class UsersJoinStep {

    public static Long 회원_가입_요청하고_아이디_반환(UserJoinRequest request) {
        final ExtractableResponse<Response> response = 회원_가입_요청(request);
        String location = response.header("Location");
        return Long.valueOf(location.split("/")[2]);
    }

    public static ExtractableResponse<Response> 회원_가입_요청(UserJoinRequest request) {
        return RestAssured.given()
                .log().all()
                .contentType(JSON)
                .body(request)

                .when()
                .post("/users")

                .then()
                .log().all()
                .extract();
    }
}
