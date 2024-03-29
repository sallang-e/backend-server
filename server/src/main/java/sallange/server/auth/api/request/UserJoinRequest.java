package sallange.server.auth.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserJoinRequest {

    private final String loginID;
    private final String loginPassword;
}
