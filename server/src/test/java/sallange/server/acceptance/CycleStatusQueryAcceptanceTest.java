package sallange.server.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import sallange.server.api.request.CycleStatusQueryRequest;
import sallange.server.api.request.CycleStatusUpdateRequest;
import sallange.server.api.request.RentRequest;
import sallange.server.auth.api.request.UserJoinRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.OK;
import static sallange.server.acceptance.step.CycleCreateStep.살랑이_생성_요청하고_아이디_반환;
import static sallange.server.acceptance.step.CycleRentStep.살랑이_대여_요청;
import static sallange.server.acceptance.step.CycleReturnStep.살랑이_반납_요청;
import static sallange.server.acceptance.step.CycleStatusQueryStep.살랑이_상태_조회_요청;
import static sallange.server.acceptance.step.CycleStatusUpdateStep.살랑이_상태_변경_요청;
import static sallange.server.acceptance.step.UsersJoinStep.회원_가입_요청하고_액세스_토큰_반환;

class CycleStatusQueryAcceptanceTest extends AcceptanceTest {

    @Test
    void 처음_생성된_살랑이의_상태는_AVAILABLE_이다() {
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();
        final ExtractableResponse<Response> response = 살랑이_상태_조회_요청(new CycleStatusQueryRequest(cycleId));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
                () -> assertThat(response.jsonPath().getString("status")).isEqualTo("AVAILABLE")
        );
    }

    @Test
    void 생성_후_대여된_살랑이의_상태는_RENT_이다() {
        final String accessToken = 회원_가입_요청하고_액세스_토큰_반환(new UserJoinRequest("깃짱", "KAKAO", 123L, 2));
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();
        살랑이_대여_요청(accessToken, RentRequest.from(cycleId));

        final ExtractableResponse<Response> response = 살랑이_상태_조회_요청(new CycleStatusQueryRequest(cycleId));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
                () -> assertThat(response.jsonPath().getString("status")).isEqualTo("RENT")
        );
    }

    @Test
    void 생성_후_고장_처리된_살랑이의_상태는_BROKEN이다() {
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();
        살랑이_상태_변경_요청(new CycleStatusUpdateRequest(cycleId, "BROKEN"));

        final ExtractableResponse<Response> response = 살랑이_상태_조회_요청(new CycleStatusQueryRequest(cycleId));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
                () -> assertThat(response.jsonPath().getString("status")).isEqualTo("BROKEN")
        );
    }

    @Test
    void 대여_후_반납된_살랑이의_상태는_AVAILABLE이다() {
        final String accessToken = 회원_가입_요청하고_액세스_토큰_반환(new UserJoinRequest("깃짱", "KAKAO", 123L, 2));
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();

        살랑이_대여_요청(accessToken, new RentRequest(String.valueOf(cycleId)));
        살랑이_반납_요청(accessToken);

        final ExtractableResponse<Response> response = 살랑이_상태_조회_요청(new CycleStatusQueryRequest(cycleId));
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
                () -> assertThat(response.jsonPath().getString("status")).isEqualTo("AVAILABLE")
        );
    }
}
