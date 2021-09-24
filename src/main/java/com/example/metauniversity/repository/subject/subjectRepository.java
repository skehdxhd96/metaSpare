package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.subject.subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface subjectRepository extends JpaRepository<subject, Long> , subjectRepositoryCustom {
}
