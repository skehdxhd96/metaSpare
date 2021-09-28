package com.example.metauniversity.util;

import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AboutTime {

    public static Boolean BooleanEnroll(subject subject, List<timeTable> mySubjectList) {
        for (timeTable mySubject : mySubjectList) {
            if(mySubject.getSubject().getDay().equals(subject.getDay())) { //요일이 같으면
                if(compareTime(subject.getEndTime(), mySubject.getSubject().getStartTime()) >= 0
                || compareTime(mySubject.getSubject().getEndTime(), subject.getStartTime()) >= 0) {
                    continue;
                }
                return false;
            }
            return false;
        }
        return true;
    }

    public static Boolean BooleanCreateSubject(subjectDto.create createDto, List<subject> allByClassRoom) {
        for (subject subject : allByClassRoom) {
            if(subject.getDay().equals(createDto.getDay())) {
                if(compareTime(createDto.getEndTime(), subject.getStartTime()) >= 0
                || compareTime(subject.getEndTime(), createDto.getStartTime()) >= 0) {
                    continue;
                }
                return false;
            }
            return false;
        }
        return true;
    }

    public static Long compareTime(String time1, String time2) {
        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);
        return ChronoUnit.HOURS.between(t1, t2);
    }
}
