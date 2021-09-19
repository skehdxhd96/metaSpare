package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import static com.example.metauniversity.domain.User.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public User findByUserName(String userName) {
        return queryFactory.select(user)
                .from(user)
                .join(user.usersData).fetchJoin()
                .where(user.usersData.userName.eq(userName))
                .fetchOne();
    }

    @Override
    public User getMyInfo(Long id) {
        return queryFactory.select(user)
                .from(user)
                .join(user.usersData).fetchJoin()
                .where(user.id.eq(id))
                .fetchOne();
    }
}
