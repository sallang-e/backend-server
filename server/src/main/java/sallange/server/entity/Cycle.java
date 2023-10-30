package sallange.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cycle extends BaseDate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private CycleStatus status;

    public Cycle(final CycleStatus status) {
        this.status = status;
    }

    public void rent() {
        this.status = CycleStatus.RENT;
    }

    public void available() {
        this.status = CycleStatus.AVAILABLE;
    }

    public boolean isRent() {
        return this.status == CycleStatus.RENT;
    }

    public boolean isBroken() {
        return this.status == CycleStatus.BROKEN;
    }
}
