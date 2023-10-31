package sallange.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sallange.server.api.request.UserJoinRequest;
import sallange.server.auth.OAuthProvider;
import sallange.server.entity.User;
import sallange.server.repository.UsersRepository;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersJoinController {

    private final UsersRepository usersRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> join(@RequestBody final UserJoinRequest request) {
        final User user = usersRepository.save(
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
