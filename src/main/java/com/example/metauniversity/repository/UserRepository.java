package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{
    Optional<User> findByAccountId(String accountId);
}
