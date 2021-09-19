package com.example.metauniversity.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserTyped {
    STUDENT("ROLE_STUDENT", "학생"),
    WORKER("ROLE_WORKER", "임직원");

    private final String key;
    private final String value;
}
