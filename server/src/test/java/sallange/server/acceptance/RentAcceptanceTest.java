package sallange.server.acceptance;

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

import static sallange.server.acceptance.step.RentStep.살랑이_대여_요청;
import static sallange.server.acceptance.step.UsersJoinStep.회원_가입_요청하고_아이디_반환;

class RentAcceptanceTest extends AcceptanceTest {

    @Autowired
    private CycleRepository cycleRepository;

    @Test
    void 살랑이_정상_대여() {
        final Long userId = 회원_가입_요청하고_아이디_반환(new UserJoinRequest("깃짱", "KAKAO", 123L, 2));
        final Cycle availableCycle = cycleRepository.save(new Cycle(CycleStatus.AVAILABLE));
        final ExtractableResponse<Response> response = 살랑이_대여_요청(userId, new RentRequest(String.valueOf(availableCycle.getId())));

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
