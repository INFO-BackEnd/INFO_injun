package org.example.info_injun.domain.user.domain.repository;

import org.example.info_injun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
