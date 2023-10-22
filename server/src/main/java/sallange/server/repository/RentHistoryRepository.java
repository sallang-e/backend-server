package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.RentHistory;

public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {
}
