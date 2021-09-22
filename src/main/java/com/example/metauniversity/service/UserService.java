package com.example.metauniversity.service;

import com.example.metauniversity.config.FolderConfig;
import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.UserFile;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UsersData;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.UserFileRepository;
import com.example.metauniversity.repository.UserRepository;
import com.example.metauniversity.repository.UsersDataRepository;
import com.example.metauniversity.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UsersDataRepository usersDataRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserFileRepository userFileRepository;
    private final FileService fileService;
    private final FolderConfig folderConfig;

    @Transactional
    public void saveUser(userDto.signIn signindto) {

        /**
         * 1. signindto.getUserCode(입력받은번호(학생은 학번, 사원은 사번))과 UsersData.getUserCode가 같다면 회원가입 OK
         * 1-1. UsersData.getUserCode가 null이 나온다면 <허가된 학번 혹은 사번이 아닙니다. 행정실을 통한 문의 바랍니다.> 오류 출력
         * 1-2. 만약 둘이 다르다면 <학번 혹은 사번이 다릅니다. 행정실을 통한 문의 바랍니다.> 오류 출력
         */

        UsersData InitialUserData = usersDataRepository.findByUserCode(signindto.getUserCode())
                .orElseThrow(() -> new NoSuchUserException("허가된 학번 혹은 사번이 아닙니다. 행정실을 통한 문의 바랍니다."));

        if(!InitialUserData.getUserEmail().equals(signindto.getUserEmail())) {
            throw new NoSuchUserException("오류메세지 나중에 정할게여..");
        }

        customUserDetailsService.save(signindto, InitialUserData);
    }

    public userDto.getMyInfoResponse getUserInfo(Long id) {
        return new userDto.getMyInfoResponse(userRepository.getMyInfo(id));
    }

    @Transactional
    public userDto.updateResponse updateMyInfo(userDto.update updateDto, User user) {

        File file = fileService.uploadThumbnailImage(folderConfig.getUser(), updateDto.getThumbnail());
        userFileRepository.save(UserFile.create(file, user));

        return  userDto.updateResponse.builder()
                .thumbnailUrl(file.getUrl())
                .build();
    }
}
