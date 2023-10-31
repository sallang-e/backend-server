package sallange.server.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import sallange.server.api.request.CycleStatusUpdateRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static sallange.server.acceptance.step.CycleCreateStep.살랑이_생성_요청하고_아이디_반환;
import static sallange.server.acceptance.step.CycleStatusUpdateStep.살랑이_상태_변경_요청;

class CycleStatusUpdateAcceptanceTest extends AcceptanceTest {

    @Test
    void 살랑이의_상태를_변경할_수_있다() {
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();
        final ExtractableResponse<Response> response = 살랑이_상태_변경_요청(new CycleStatusUpdateRequest(cycleId, "BROKEN"));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
