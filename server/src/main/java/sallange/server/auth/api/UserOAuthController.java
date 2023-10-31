package sallange.server.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.auth.application.UserOAuthService;

import java.net.URI;

import static org.springframework.http.HttpStatus.FOUND;

@RequiredArgsConstructor
@RestController
@Profile("!test")
@RequestMapping("/api/login")
public class UserOAuthController {

    private final UserOAuthService userOAuthService;

    @GetMapping("kakao")
    public ResponseEntity<Void> loginKakao() {
        final String redirectUri = userOAuthService.loginRedirectUri();
        return ResponseEntity.status(FOUND)
                .location(URI.create(redirectUri))
                .build();
    }
}
