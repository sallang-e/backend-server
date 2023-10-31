package sallange.server.auth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.auth.OAuthProvider;
import sallange.server.auth.api.request.UserJoinRequest;
import sallange.server.entity.User;
import sallange.server.repository.UserRepository;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserJoinController {

    private final UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> join(@RequestBody final UserJoinRequest request) {
        final User user = userRepository.save(
                new User(
                        request.getName(),
                        OAuthProvider.from(request.getoAuthProvider()),
                        request.getoAuthId(),
                        request.getLeftRentCount()
                )
        );

        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .build();
    }
}
