package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.UsersData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersDataRepository extends JpaRepository<UsersData, String> {

    Optional<UsersData> findByUserCode(String userCode);
}
