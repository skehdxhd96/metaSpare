package com.example.metauniversity.domain.subject.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class subjectData<T> {

    private Integer count;
    private T data;
}
