package sallange.server.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import sallange.server.api.request.RentRequest;
import sallange.server.api.request.UserJoinRequest;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;
import sallange.server.repository.CycleRepository;

import static io.restassured.http.ContentType.JSON;

class RentAcceptanceTest extends AcceptanceTest {

    @Autowired
    private CycleRepository cycleRepository;

    @Test
    void 살랑이_정상_대여() {
        final ExtractableResponse<Response> userJoin = RestAssured.given()
                .log().all()
                .contentType(JSON)
                .body(new UserJoinRequest("깃짱", "KAKAO", 123L, 2))

                .when()
                .post("/util/users")

                .then()
                .log().all()
                .extract();

        String location = userJoin.header("Location");
        final Long userId = Long.valueOf(location.split("/")[2]);
        final Cycle availableCycle = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));

        final ExtractableResponse<Response> response = RestAssured.given()
                .log().all()
                .contentType(JSON)
                .body(new RentRequest(String.valueOf(availableCycle.getId())))

                .when()
                .post("/rent/" + userId)

                .then()
                .log().all()
                .extract();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
