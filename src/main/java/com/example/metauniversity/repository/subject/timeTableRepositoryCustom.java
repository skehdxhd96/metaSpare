package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.subject.timeTable;

import java.util.List;
import java.util.Optional;

public interface timeTableRepositoryCustom {

    Optional<timeTable> getMySubject(Long userId, Long subjectId);
    List<timeTable> getMySubjectToEnroll(Long userId);
    Boolean exist(Long subjectId, Long userId);
    timeTable getMyPreSubject(Long subjectId, Long userId);
    List<timeTable> getAllMySubject(Long userId);
}
