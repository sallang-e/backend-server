package sallange.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sallange.server.entity.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
