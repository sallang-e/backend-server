package sallange.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cycle {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private CycleStatus status;
}
