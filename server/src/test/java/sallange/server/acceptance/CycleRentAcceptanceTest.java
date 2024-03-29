package sallange.server.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import sallange.server.api.request.RentRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static sallange.server.acceptance.step.CycleCreateStep.살랑이_생성_요청하고_아이디_반환;
import static sallange.server.acceptance.step.CycleRentStep.살랑이_대여_요청;
import static sallange.server.acceptance.step.CycleReturnStep.살랑이_반납_요청;
import static sallange.server.acceptance.step.UsersJoinStep.USER_JOIN_REQUEST_GITCHAN;
import static sallange.server.acceptance.step.UsersJoinStep.USER_JOIN_REQUEST_HOONCHAN;
import static sallange.server.acceptance.step.UsersJoinStep.회원_가입_요청하고_액세스_토큰_반환;

class CycleRentAcceptanceTest extends AcceptanceTest {

    @Test
    void 살랑이를_대여한다() {
        final String accessToken = 회원_가입_요청하고_액세스_토큰_반환(USER_JOIN_REQUEST_GITCHAN);
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();

        final ExtractableResponse<Response> response = 살랑이_대여_요청(accessToken, RentRequest.from(cycleId));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 이미_다른_사람이_대여중인_살랑이를_대여하면_에러_코드_1을_반환한다() {
        final String user1AccessToken = 회원_가입_요청하고_액세스_토큰_반환(USER_JOIN_REQUEST_GITCHAN);
        final String user2AccessToken = 회원_가입_요청하고_액세스_토큰_반환(USER_JOIN_REQUEST_HOONCHAN);

        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();
        살랑이_대여_요청(user1AccessToken, RentRequest.from(cycleId));

        final ExtractableResponse<Response> response = 살랑이_대여_요청(user2AccessToken, RentRequest.from(cycleId));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(response.jsonPath().getInt("errorCode")).isEqualTo(1)
        );
    }

    @Test
    void 살랑이_사용_가능_회수가_없는_유저가_살랑이를_대여하면_에러_코드_3을_반환한다() {
        final String accessToken = 회원_가입_요청하고_액세스_토큰_반환(USER_JOIN_REQUEST_GITCHAN);
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();

        살랑이_대여_요청(accessToken, RentRequest.from(cycleId));
        살랑이_반납_요청(accessToken);
        살랑이_대여_요청(accessToken, RentRequest.from(cycleId));
        살랑이_반납_요청(accessToken);

        final ExtractableResponse<Response> response = 살랑이_대여_요청(accessToken, RentRequest.from(cycleId));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(response.jsonPath().getInt("errorCode")).isEqualTo(3)
        );
    }

    @Test
    void 이미_다른_살랑이를_대여하면서_또_다른_살랑이를_대여하면_에러_코드_4를_반환하다() {
        final String accessToken = 회원_가입_요청하고_액세스_토큰_반환(USER_JOIN_REQUEST_GITCHAN);

        final Long cycle1Id = 살랑이_생성_요청하고_아이디_반환();
        final Long cycle2Id = 살랑이_생성_요청하고_아이디_반환();

        살랑이_대여_요청(accessToken, RentRequest.from(cycle1Id));

        final ExtractableResponse<Response> response = 살랑이_대여_요청(accessToken, RentRequest.from(cycle2Id));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(response.jsonPath().getInt("errorCode")).isEqualTo(4)
        );
    }

    @Test
    void 이미_다른_살랑이를_대여하면서_또_해당_살랑이를_대여하면_에러_코드_4를_반환하다() {
        final String accessToken = 회원_가입_요청하고_액세스_토큰_반환(USER_JOIN_REQUEST_GITCHAN);
        final Long cycleId = 살랑이_생성_요청하고_아이디_반환();

        살랑이_대여_요청(accessToken, RentRequest.from(cycleId));

        final ExtractableResponse<Response> response = 살랑이_대여_요청(accessToken, RentRequest.from(cycleId));

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(response.jsonPath().getInt("errorCode")).isEqualTo(4)
        );
    }
}
