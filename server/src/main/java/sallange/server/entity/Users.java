package sallange.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import sallange.server.auth.OAuthProvider;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Users extends BaseDate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @Enumerated(STRING)
    @Column(name = "oauth_provider")
    private OAuthProvider oAuthProvider;

    @Column(name = "oauth_id")
    private Long oAuthId;
    private Integer leftRentCount;

    public Users(final Long id, final String name, final OAuthProvider oAuthProvider, final Long oAuthId, final Integer leftRentCount) {
        this.id = id;
        this.name = name;
        this.oAuthProvider = oAuthProvider;
        this.oAuthId = oAuthId;
        this.leftRentCount = leftRentCount;
    }

    public Users(final String name, final OAuthProvider oAuthProvider, final Long oAuthId, final Integer leftRentCount) {
        this(null, name, oAuthProvider, oAuthId, leftRentCount);
    }

    public Users() {
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
}
