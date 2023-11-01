package sallange.server.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.auth.api.request.AuthRequest;
import sallange.server.auth.api.request.UserJoinRequest;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.application.UserAuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/join")
    public ResponseEntity<AuthTokensResponse> join(@RequestBody final UserJoinRequest request) {
        return ResponseEntity
                .ok()
                .body(userAuthService.join(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokensResponse> login(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(userAuthService.login(request));
    }
}
