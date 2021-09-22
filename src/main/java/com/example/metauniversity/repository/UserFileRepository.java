package com.example.metauniversity.repository;

import com.example.metauniversity.domain.File.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
}
