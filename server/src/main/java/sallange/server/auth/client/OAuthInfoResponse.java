package sallange.server.auth.client;

import sallange.server.auth.OAuthProvider;

public interface OAuthInfoResponse {

    String getNickname();

    String getEmail();

    OAuthProvider getOAuthProvider();

    Long getOAuthId();
}
