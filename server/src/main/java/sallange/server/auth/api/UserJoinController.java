package sallange.server.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.auth.api.request.UserJoinRequest;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.application.UserJoinService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/users")
public class UserJoinController {

    private final UserJoinService userJoinService;

    @PostMapping
    @Transactional
    public ResponseEntity<AuthTokensResponse> join(@RequestBody final UserJoinRequest request) {
        return ResponseEntity
                .ok()
                .body(userJoinService.join(request));
    }
}
