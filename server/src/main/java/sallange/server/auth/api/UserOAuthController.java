package sallange.server.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.application.UserOAuthService;
import sallange.server.auth.application.UserOAuthLoginService;
import sallange.server.auth.util.KakaoLoginParams;

import java.net.URI;

import static org.springframework.http.HttpStatus.FOUND;

@RequiredArgsConstructor
@RestController
@Profile("!test")
@RequestMapping("/api/login")
public class UserOAuthController {

    private final UserOAuthService userOAuthService;
    private final UserOAuthLoginService userOAuthLoginService;

    @GetMapping("/kakao")
    public ResponseEntity<Void> loginKakao() {
        final String redirectUri = userOAuthService.loginRedirectUri();
        return ResponseEntity.status(FOUND)
                .location(URI.create(redirectUri))
                .build();
    }

    @GetMapping("/kakao/token")
    public ResponseEntity<AuthTokensResponse> authorizeUser(
            @RequestParam("code") String authorizationCode
    ) {
        final KakaoLoginParams params = new KakaoLoginParams(authorizationCode);
        return ResponseEntity.ok(userOAuthLoginService.login(params));
    }
}
