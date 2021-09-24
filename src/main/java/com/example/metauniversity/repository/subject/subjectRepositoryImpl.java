package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.subject.QtimeTable;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;
import com.example.metauniversity.repository.subject.subjectRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.metauniversity.domain.subject.Qsubject.subject;

@RequiredArgsConstructor
public class subjectRepositoryImpl implements subjectRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<subject> findAllByClassRoom(String classRoom) {
        return queryFactory.select(subject)
                .from(subject)
                .where(subject.classRoom.eq(classRoom))
                .fetch();
    }

    @Override
    public List<subject> findAllBySearch(subjectDto.search search) {
        return queryFactory.select(subject)
                .from(subject)
                .where(subjectTitleContains(search.getSubjectTitle()),
                        subjectDepartmentContains(search.getSubjectDepartment()),
                        subjectPointsEq(search.getSubjectPoints()),
                        isMajorEq(search.getIsMajor()))
                .fetch();
    }

    @Override
    public List<subject> getAllSubject() {
        return queryFactory.select(subject)
                .from(subject)
                .join(subject.user).fetchJoin()
                .fetch();
    }

    private BooleanExpression subjectTitleContains(String subjectTitle) {
        if(subjectTitle == null) {
            return null;
        }

        return subject.subjectTitle.contains(subjectTitle);
    }



    private BooleanExpression subjectDepartmentContains(String subjectDepartment) {
        if(subjectDepartment == null) {
            return null;
        }

        return subject.subjectDepartment.contains(subjectDepartment);
    }

    private BooleanExpression subjectPointsEq(Integer subjectPoints) {
        if(subjectPoints == null) {
            return null;
        }

        return subject.subjectPoints.eq(subjectPoints);
    }

    private BooleanExpression isMajorEq(Boolean isMajor) {
        if(isMajor == null) {
            subject.isMajor.eq(true);
        }

        return subject.isMajor.eq(isMajor);
    }
}
