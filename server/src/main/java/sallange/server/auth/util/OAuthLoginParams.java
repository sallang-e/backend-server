package sallange.server.auth.util;

import org.springframework.util.MultiValueMap;
import sallange.server.auth.OAuthProvider;

public interface OAuthLoginParams {

    OAuthProvider oAuthProvider();

    MultiValueMap<String, String> makeBody();
}
