package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.RentHistory;
import sallange.server.entity.RentType;

import java.util.Optional;

public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    Optional<RentHistory> findByUserIdAndType(Long userId, RentType type);
}
