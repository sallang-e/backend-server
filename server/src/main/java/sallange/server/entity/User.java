package sallange.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import sallange.server.auth.OAuthProvider;
import sallange.server.exception.UnAuthorizationException;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class User extends BaseDate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @Enumerated(STRING)
    @Column(name = "oauth_provider")
    private OAuthProvider oAuthProvider;

    @Column(name = "oauth_id")
    private Long oAuthId;
    private String loginId;
    private String encryptedPassword;
    private Integer leftRentCount;

    public User(final Long id, final String name, final OAuthProvider oAuthProvider, final Long oAuthId, final String loginId, final String encryptedPassword, final Integer leftRentCount) {
        this.id = id;
        this.name = name;
        this.oAuthProvider = oAuthProvider;
        this.oAuthId = oAuthId;
        this.loginId = loginId;
        this.encryptedPassword = encryptedPassword;
        this.leftRentCount = leftRentCount;
    }

    private User(final Long id, final String name, final OAuthProvider oAuthProvider, final Long oAuthId, final Integer leftRentCount) {
        this(id, name, oAuthProvider, oAuthId, null, null, leftRentCount);
    }

    public User(final String name, final OAuthProvider oAuthProvider, final Long oAuthId, final Integer leftRentCount) {
        this(null, name, oAuthProvider, oAuthId, leftRentCount);
    }

    public User(final String name, final OAuthProvider oAuthProvider, final Long oAuthId) {
        this(null, name, oAuthProvider, oAuthId, 2);
    }

    @Builder(builderMethodName = "loginUserBuilder")
    public User(
            final String loginId,
            final String encryptedPassword
    ) {
        this(null, null, null, null, loginId, encryptedPassword, 2);
    }

    public User() {
    }

    public boolean isAvailable() {
        return leftRentCount >= 1;
    }

    public void reduceRentCount() {
        this.leftRentCount--;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OAuthProvider getoAuthProvider() {
        return oAuthProvider;
    }

    public Long getoAuthId() {
        return oAuthId;
    }

    public Integer getLeftRentCount() {
        return leftRentCount;
    }

    public void checkPassword(final String encryptedPassword) {
        if (!this.encryptedPassword.equals(encryptedPassword)) {
            throw new UnAuthorizationException("[ERROR] 아이디와 패스워드가 올바르지 않습니다!");
        }
    }
}
