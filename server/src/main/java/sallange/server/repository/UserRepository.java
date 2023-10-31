package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
