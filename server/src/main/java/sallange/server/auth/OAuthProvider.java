package sallange.server.auth;

import java.util.Arrays;

public enum OAuthProvider {
    KAKAO,
    GOOGLE,
    ;

    public static OAuthProvider from(final String oAuthProvider) {
        System.out.println("oAuthProvider = " + oAuthProvider);
        return Arrays.stream(OAuthProvider.values())
                .filter(it -> it.name().equalsIgnoreCase(oAuthProvider))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 올바른 OAuth 정보를 입력해 주세요."));
    }
}
