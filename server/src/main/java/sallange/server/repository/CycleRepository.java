package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.Cycle;
import sallange.server.entity.CycleStatus;

import java.util.Optional;

public interface CycleRepository extends JpaRepository<Cycle, Long> {

    Optional<Cycle> findByIdAndStatus(Long cycleId, CycleStatus cycleStatus);
}
