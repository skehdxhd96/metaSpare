package com.example.metauniversity.repository.file;

import com.example.metauniversity.domain.File.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}