package com.example.metauniversity.repository.user;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.dto.userDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
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

    @Override
    public List<User> getUserBySearch(userDto.searchDto searchDto) {
        return queryFactory.select(user)
                .from(user)
                .join(user.usersData).fetchJoin()
                .join(user.userfile).fetchJoin()
                .where(userCodeContains(searchDto.getUserCode()),
                        userNameContains(searchDto.getUserName()),
                        userDepartmentContains(searchDto.getUserDepartment()),
                        userMajorContains(searchDto.getUserMajor()))
                .fetch();
    }

    private BooleanExpression userCodeContains(String userCode) {
        if(userCode == null) {
            return null;
        }

        return user.usersData.userCode.contains(userCode);
    }

    private BooleanExpression userMajorContains(String userMajor) {
        if(userMajor == null) {
            return null;
        }

        return user.usersData.userMajor.contains(userMajor);
    }

    private BooleanExpression userDepartmentContains(String userDepartment) {
        if(userDepartment == null) {
            return null;
        }

        return user.usersData.userDepartment.contains(userDepartment);
    }

    private BooleanExpression userNameContains(String userName) {
        if(userName == null) {
            return null;
        }

        return user.usersData.userName.contains(userName);
    }
}