package org.example.codetohire.repository;

import org.example.codetohire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepo  extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

}
