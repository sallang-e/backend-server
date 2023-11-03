package sallange.server.auth.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sallange.server.auth.OAuthProvider;
import sallange.server.auth.client.OAuthClient;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Profile("!test")
public class UserGoogleOAuthService {

    private final String clientId;
    private final String baseUri;
    private final String apiUri;
    private final String redirectUri;
    private final Map<OAuthProvider, OAuthClient> clients;

    public UserGoogleOAuthService(
            @Value("${oauth.google.client-id}") String clientId,
            @Value("${oauth.google.redirect-uri}") String redirectUri,
            @Value("${oauth.google.url.auth}") String baseUri,
            @Value("${oauth.google.url.api}") String apiUri,
            List<OAuthClient> clients
    ) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.baseUri = baseUri;
        this.apiUri = apiUri;
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(
                        OAuthClient::oAuthProvider,
                        Function.identity()
                )
        );
    }
}
