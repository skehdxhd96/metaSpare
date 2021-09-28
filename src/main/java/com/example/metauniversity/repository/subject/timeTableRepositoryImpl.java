package com.example.metauniversity.repository.subject;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.subject.timeTable;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.example.metauniversity.domain.subject.QtimeTable.timeTable;

@RequiredArgsConstructor
public class timeTableRepositoryImpl implements timeTableRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<timeTable> getMySubject(Long userId, Long subjectId) {
        return Optional.ofNullable(queryFactory.select(timeTable)
                .from(timeTable)
                .where(timeTable.subject.id.eq(subjectId)
                        .and(timeTable.user.id.eq(userId)))
                .fetchOne());
    }

    @Override
    public List<timeTable> getMySubjectToEnroll(Long userId) {
        return queryFactory.select(timeTable)
                .from(timeTable)
                .join(timeTable.subject).fetchJoin()
                .where(timeTable.user.id.eq(userId)
                        .and(timeTable.status.eq(true)))
                .fetch();
    }

    /**
     * 이미 수강 버튼을 누른적이 있었다면 실행되는 쿼리
     * == 테이블에 수강상태 false값으로 있는 row가 있는지를 조회
     */
    @Override
    public Boolean exist(Long subjectId, Long userId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(timeTable)
                .where(timeTable.subject.id.eq(subjectId)
                        .and(timeTable.user.id.eq(userId)
                                .and(timeTable.status.eq(false))))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public timeTable getMyPreSubject(Long subjectId, Long userId) {
        return queryFactory.select(timeTable)
                .from(timeTable)
                .join(timeTable.subject).fetchJoin()
                .where(timeTable.subject.id.eq(subjectId)
                        .and(timeTable.user.id.eq(userId)
                                .and(timeTable.status.eq(false))))
                .fetchOne();
    }

    @Override
    public List<timeTable> getAllMySubject(Long userId) {
        return queryFactory.select(timeTable)
                .from(timeTable)
                .join(timeTable.subject).fetchJoin()
                .join(timeTable.user).fetchJoin()
                .where(timeTable.user.id.eq(userId)
                        .and(timeTable.status.eq(true)))
                .fetch();
    }
}
