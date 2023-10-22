package sallange.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sallange.server.auth.OAuthProvider;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

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

    public boolean isAvailable() {
        return leftRentCount >= 1;
    }
}
