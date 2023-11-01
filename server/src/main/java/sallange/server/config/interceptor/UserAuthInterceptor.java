package sallange.server.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sallange.server.auth.util.AuthTokensGenerator;
import sallange.server.auth.util.BearerParser;
import sallange.server.exception.UnAuthorizationException;
import sallange.server.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserAuthInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!BearerParser.isBearerAuthType(authorizationHeader)) {
            throw new UnAuthorizationException("[ERROR] 유저에 인증 헤더 정보가 포함되어 있지 않습니다.");
        }

        final String accessToken = BearerParser.parseAuthorization(authorizationHeader);
        if (!authTokensGenerator.isValidToken(accessToken)) {
            throw new UnAuthorizationException("[ERROR] 유효한 토큰이 아닙니다!");
        }

        final Long userId = authTokensGenerator.extractUserId(accessToken);
        userRepository.findById(userId)
                .orElseThrow(() -> new UnAuthorizationException("[ERROR] 해당 유저는 존재하지 않습니다."));

        return true;
    }
}
