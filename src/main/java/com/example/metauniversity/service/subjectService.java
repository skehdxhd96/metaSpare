package com.example.metauniversity.service;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;
import com.example.metauniversity.exception.CannotAddSubjectException;
import com.example.metauniversity.exception.NoCreateSubjectException;
import com.example.metauniversity.exception.NoSuchSubjectException;
import com.example.metauniversity.exception.NoSuchTimeTableException;
import com.example.metauniversity.repository.subject.subjectRepository;
import com.example.metauniversity.repository.subject.timeTableRepository;
import com.example.metauniversity.util.AboutTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class subjectService {

    private final subjectRepository subjectRepository;
    private final timeTableRepository timeTableRepository;

    @Transactional
    public void addSubject(subjectDto.create createDto, User user) {

        /**
         * 예외 처리
         * 강의실이 같은 subject 데이터를 불러와서
         * 요일-시간 겹치면 오류
         */

        if(!user.getUsersData().getUserType().equals(UserTyped.WORKER)) {
            throw new NoCreateSubjectException("관리자만 등록이 가능합니다.");
        }

        if(AboutTime.BooleanCreateSubject(createDto, subjectRepository.findAllByClassRoom(createDto.getClassRoom()))) {
            System.out.println("isMajor : " + createDto.getIsMajor());
            subjectRepository.save(subject.create(createDto, user));
        } else {
            throw new NoCreateSubjectException("해당 강의실의 원하시는 시간대는 이미 예약 되었습니다.");
        }
    }

    public List<subjectDto.getList> getAll() {
        return subjectRepository.getAllSubject().stream()
                .map(s -> new subjectDto.getList(s)).collect(Collectors.toList());
    }

    public List<subjectDto.getList> getMySubject(User user) {
        return timeTableRepository.getAllMySubject(user.getId()).stream()
                .map(s -> new subjectDto.getList(s)).collect(Collectors.toList());
    }

    public List<subjectDto.getList> getAllBySearch(subjectDto.search searchDto) {
        return subjectRepository.findAllBySearch(searchDto)
                .stream().map(s -> new subjectDto.getList(s)).collect(Collectors.toList());
    }

    @Transactional
    public subjectDto.enroll EnrollSubject(Long subjectId, User user) {

        Integer currentPoints = 0;
        subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NoSuchSubjectException("등록되어있는 수업이 아닙니다."));
        List<timeTable> mySubjectList = timeTableRepository.getMySubjectToEnroll(user.getId()); // 현재 내가 수강신청한 과목 목록
        for (timeTable timeTable : mySubjectList) {
            currentPoints += timeTable.getSubject().getSubjectPoints();
        }

        if(!AboutTime.BooleanEnroll(subject, mySubjectList)) {
            throw new CannotAddSubjectException("시간대 중복입니다.");
        }

        if(currentPoints + subject.getSubjectPoints() < 18) {
            // 현재 학점 + 선택한 과목 학점이 18학점 미만이라면
            if(timeTableRepository.exist(subjectId, user.getId())) {
                //과거에 신청한 적이 있다(테이블이 수강신청상태 == false인 row가 존재한다.
                timeTableRepository.getMyPreSubject(subjectId, user.getId()).setStatus();
            }

            timeTable myNewTimeTable = timeTable.builder()
                    .user(user)
                    .subject(subject)
                    .build();

            timeTableRepository.save(myNewTimeTable);

            return subjectDto.enroll.builder()
                    .subjectTitle(myNewTimeTable.getSubject().getSubjectTitle())
                    .status(myNewTimeTable.getStatus())
                    .build();
        } else {
            throw new CannotAddSubjectException("신청 학점이 18학점을 넘을 수 없습니다.");
        }
    }

    @Transactional
    public subjectDto.enroll cancelSubject(Long subjectId, Long userId) {
        timeTable mySubject = timeTableRepository.getMySubject(userId, subjectId)
                .orElseThrow(() -> new NoSuchTimeTableException("현재 수강목록에 없습니다."));
        mySubject.setStatus();

        return new subjectDto.enroll(mySubject.getSubject().getSubjectTitle(), mySubject.getStatus());
    }
}
