package com.stockPrice.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockPrice.app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
