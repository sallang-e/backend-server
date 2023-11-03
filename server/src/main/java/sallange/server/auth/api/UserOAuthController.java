package sallange.server.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.application.UserGoogleOAuthService;
import sallange.server.auth.application.UserKakaoOAuthService;
import sallange.server.auth.application.UserOAuthLoginService;
import sallange.server.auth.util.GoogleLoginParams;
import sallange.server.auth.util.KakaoLoginParams;

import java.net.URI;

import static org.springframework.http.HttpStatus.FOUND;

@RequiredArgsConstructor
@RestController
@Profile("!test")
@RequestMapping("/api/login")
public class UserOAuthController {

    private final UserKakaoOAuthService userKakaoOAuthService;
    private final UserGoogleOAuthService userGoogleOAuthService;
    private final UserOAuthLoginService userOAuthLoginService;

    @GetMapping("/kakao")
    public ResponseEntity<Void> loginKakao() {
        final String redirectUri = userKakaoOAuthService.loginRedirectUri();
        return ResponseEntity.status(FOUND)
                .location(URI.create(redirectUri))
                .build();
    }

    @GetMapping("/kakao/token")
    public ResponseEntity<AuthTokensResponse> authorizeKakaoUser(
            @RequestParam("code") String authorizationCode
    ) {
        final KakaoLoginParams params = new KakaoLoginParams(authorizationCode);
        return ResponseEntity.ok(userOAuthLoginService.login(params));
    }

    @GetMapping("/google")
    public ResponseEntity<Void> loginGoogle() {
        final String redirectUri = userKakaoOAuthService.loginRedirectUri();
        return ResponseEntity.status(FOUND)
                .location(URI.create(redirectUri))
                .build();
    }

    @GetMapping("/google/token")
    public ResponseEntity<AuthTokensResponse> authorizeGoogleUser(
            @RequestParam("code") String authorizationCode
    ) {
        final GoogleLoginParams params = new GoogleLoginParams(authorizationCode);
        return ResponseEntity.ok(userOAuthLoginService.login(params));
    }
}
