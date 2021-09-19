package com.example.metauniversity.repository;

import com.example.metauniversity.domain.User.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    User findByUserName(String userName);

    User getMyInfo(Long id);
}
