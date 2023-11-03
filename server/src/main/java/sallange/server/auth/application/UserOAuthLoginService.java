package sallange.server.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.client.OAuthInfoResponse;
import sallange.server.auth.util.AuthTokensGenerator;
import sallange.server.auth.util.OAuthLoginParams;
import sallange.server.entity.User;
import sallange.server.repository.UserRepository;

@RequiredArgsConstructor
@Profile("!test")
@Service
public class UserOAuthLoginService {

    private final UserOAuthService userOAuthService;
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public AuthTokensResponse login(final OAuthLoginParams params) {
        final OAuthInfoResponse oAuthInfoResponse = userOAuthService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(final OAuthInfoResponse oauthInfo) {
        return userRepository.findIdByOAuthProviderAndOAuthId(
                oauthInfo.getOAuthProvider(),
                oauthInfo.getOAuthId()
        ).orElseGet(() -> createCustomer(oauthInfo));
    }

    private Long createCustomer(final OAuthInfoResponse oauthInfo) {
        final User user = new User(
                oauthInfo.getNickname(),
                oauthInfo.getOAuthProvider(),
                oauthInfo.getOAuthId()
        );
        return userRepository.save(user).getId();
    }
}
