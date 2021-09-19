package com.example.metauniversity.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnrollmentStatus {
    ATTENDING("ENROLLMENT_ATTENDING", "학생"),
    LEAVE("ENROLLMENT_LEAVE", "임직원"),
    GRADUATED("ENROLLMENT_GRADUATED", "임직원");

    private final String key;
    private final String value;
}
