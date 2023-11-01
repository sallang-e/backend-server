package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sallange.server.auth.OAuthProvider;
import sallange.server.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT c.id FROM User c WHERE c.oAuthProvider = :oAuthProvider AND c.oAuthId = :oAuthId")
    Optional<Long> findIdByOAuthProviderAndOAuthId(
            @Param("oAuthProvider") OAuthProvider oAuthProvider,
            @Param("oAuthId") Long oAuthId
    );

    Optional<User> findByLoginId(String loginId);
}
