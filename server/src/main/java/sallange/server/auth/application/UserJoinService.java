package sallange.server.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sallange.server.auth.OAuthProvider;
import sallange.server.auth.api.request.UserJoinRequest;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.util.AuthTokensGenerator;
import sallange.server.entity.User;
import sallange.server.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class UserJoinService {

    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public AuthTokensResponse join(final UserJoinRequest request) {
        final User user = userRepository.save(
                new User(
                        request.getName(),
                        OAuthProvider.from(request.getoAuthProvider()),
                        request.getoAuthId(),
                        request.getLeftRentCount()
                )
        );

        return authTokensGenerator.generate(user.getId());
    }
}
