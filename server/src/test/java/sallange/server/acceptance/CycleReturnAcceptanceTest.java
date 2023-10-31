package sallange.server.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import sallange.server.api.request.RentRequest;
import sallange.server.auth.api.request.UserJoinRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static sallange.server.acceptance.step.CycleCreateStep.살랑이_생성_요청하고_아이디_반환;
import static sallange.server.acceptance.step.CycleRentStep.살랑이_대여_요청;
import static sallange.server.acceptance.step.CycleReturnStep.살랑이_반납_요청;
import static sallange.server.acceptance.step.UsersJoinStep.회원_가입_요청하고_아이디_반환;

class CycleReturnAcceptanceTest extends AcceptanceTest {

    @Test
    void 살랑이를_반납한다() {
        final Long userId = 회원_가입_요청하고_아이디_반환(new UserJoinRequest("깃짱", "KAKAO", 123L, 2));
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();

        살랑이_대여_요청(userId, new RentRequest(String.valueOf(cycleId)));
        final ExtractableResponse<Response> response = 살랑이_반납_요청(userId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
