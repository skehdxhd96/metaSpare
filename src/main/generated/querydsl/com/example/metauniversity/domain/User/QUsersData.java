package com.example.metauniversity.domain.User;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsersData is a Querydsl query type for UsersData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsersData extends EntityPathBase<UsersData> {

    private static final long serialVersionUID = 1798113743L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsersData usersData = new QUsersData("usersData");

    public final StringPath Address = createString("Address");

    public final EnumPath<EnrollmentStatus> enrollmentStatus = createEnum("enrollmentStatus", EnrollmentStatus.class);

    public final QUser user;

    public final StringPath userBirth = createString("userBirth");

    public final StringPath userCode = createString("userCode");

    public final StringPath userDepartment = createString("userDepartment");

    public final StringPath userEmail = createString("userEmail");

    public final NumberPath<Integer> userGrade = createNumber("userGrade", Integer.class);

    public final StringPath userMajor = createString("userMajor");

    public final StringPath userMinor = createString("userMinor");

    public final StringPath userName = createString("userName");

    public final StringPath userPhone = createString("userPhone");

    public final EnumPath<UserTyped> userType = createEnum("userType", UserTyped.class);

    public final StringPath workerSpot = createString("workerSpot");

    public QUsersData(String variable) {
        this(UsersData.class, forVariable(variable), INITS);
    }

    public QUsersData(Path<? extends UsersData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsersData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsersData(PathMetadata metadata, PathInits inits) {
        this(UsersData.class, metadata, inits);
    }

    public QUsersData(Class<? extends UsersData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

