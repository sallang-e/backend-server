package sallange.server.auth.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sallange.server.auth.OAuthProvider;
import sallange.server.auth.client.UserOAuthClient;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Profile("!test")
public class UserOAuthService {

    private final String clientId;
    private final String baseUri;
    private final String apiUri;
    private final String redirectUri;
    private final Map<OAuthProvider, UserOAuthClient> clients;

    public UserOAuthService(
            @Value("${oauth.kakao.client-id}") String clientId,
            @Value("${oauth.kakao.redirect-uri}") String redirectUri,
            @Value("${oauth.kakao.url.auth}") String baseUri,
            @Value("${oauth.kakao.url.api}") String apiUri,
            List<UserOAuthClient> clients
    ) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.baseUri = baseUri;
        this.apiUri = apiUri;
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(
                        UserOAuthClient::oAuthProvider,
                        Function.identity()
                )
        );
    }

    public String loginRedirectUri() {
        return baseUri
                + "/oauth/authorize"
                + "?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri;
    }
}
