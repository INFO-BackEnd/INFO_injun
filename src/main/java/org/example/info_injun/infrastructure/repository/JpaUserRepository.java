package org.example.info_injun.infrastructure.repository;

import org.example.info_injun.domain.user.entity.User;
import org.example.info_injun.domain.user.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Integer>, UserRepository {
}
