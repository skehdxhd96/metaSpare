package com.example.metauniversity.repository.user;

import com.example.metauniversity.domain.File.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
}