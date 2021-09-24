package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.subject.timeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface timeTableRepository extends JpaRepository<timeTable, Long>, timeTableRepositoryCustom {
}
