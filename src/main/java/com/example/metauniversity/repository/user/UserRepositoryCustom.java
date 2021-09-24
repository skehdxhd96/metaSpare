package com.example.metauniversity.repository.user;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.dto.userDto;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    User findByUserName(String userName);
    User getMyInfo(Long id);
    List<User> getUserBySearch(userDto.searchDto searchDto);
}