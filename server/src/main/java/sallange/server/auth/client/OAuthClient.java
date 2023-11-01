package sallange.server.auth.client;

import sallange.server.auth.OAuthProvider;
import sallange.server.auth.util.OAuthLoginParams;

public interface OAuthClient {

    OAuthProvider oAuthProvider();

    String requestAccessToken(OAuthLoginParams params);

    OAuthInfoResponse requestOauthInfo(String accessToken);
}
