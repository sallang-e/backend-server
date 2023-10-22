package sallange.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RentHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long userId;
    private Long cycleId;
    private RentType type;
}
