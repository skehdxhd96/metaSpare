package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;

import java.util.List;

public interface subjectRepositoryCustom {

    List<subject> findAllByClassRoom(String classRoom);
    List<subject> findAllBySearch(subjectDto.search search);
    List<subject> getAllSubject();
}
