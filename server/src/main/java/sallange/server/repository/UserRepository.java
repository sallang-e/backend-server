package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
