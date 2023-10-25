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
import sallange.server.entity.Users;
import sallange.server.repository.UsersRepository;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/util")
public class UsersJoinController {

    private final UsersRepository usersRepository;

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<Void> join(@RequestBody final UserJoinRequest request) {
        final Users users = usersRepository.save(
                new Users(
                        request.getName(),
                        OAuthProvider.from(request.getoAuthProvider()),
                        request.getoAuthId(),
                        request.getLeftRentCount()
                )
        );

        return ResponseEntity
                .created(URI.create("/users/" + users.getId()))
                .build();
    }
}
