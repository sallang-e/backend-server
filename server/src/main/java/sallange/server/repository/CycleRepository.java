package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.Cycle;

public interface CycleRepository extends JpaRepository<Cycle, Long> {
}
