package sallange.server.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sallange.server.auth.api.request.AuthRequest;
import sallange.server.auth.api.request.UserJoinRequest;
import sallange.server.auth.api.response.AuthTokensResponse;
import sallange.server.auth.application.util.PasswordEncryptor;
import sallange.server.auth.util.AuthTokensGenerator;
import sallange.server.entity.User;
import sallange.server.repository.UserRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserAuthService {

    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public AuthTokensResponse join(final UserJoinRequest request) {
        final String loginId = request.getLoginID();
        final String loginPassword = request.getLoginPassword();
        if (userRepository.findByLoginId(loginId).isPresent()) {
            throw new RuntimeException("[ERROR] 중복되는 아이디입니다.");
        }

        final String encryptedPassword = PasswordEncryptor.encrypt(loginPassword);

        final User user = User.loginUserBuilder()
                .loginId(loginId)
                .encryptedPassword(encryptedPassword)
                .build();

        final Long id = userRepository.save(user).getId();
        return authTokensGenerator.generate(id);
    }

    public AuthTokensResponse login(final AuthRequest request) {
        final String loginId = request.getLoginID();
        final String loginPassword = request.getLoginPassword();
        final User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 존재하지 않는 아이디입니다."));

        user.checkPassword(PasswordEncryptor.encrypt(loginPassword));
        return authTokensGenerator.generate(user.getId());
    }
}
