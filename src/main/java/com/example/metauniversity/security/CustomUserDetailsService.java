package com.example.metauniversity.security;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.UsersData;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NoSuchUserException("해당하는 사용자가 존재하지 않습니다."));

//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        if (user.getUsersData().getUserType().equals("STUDENT")) {
//            System.out.println("user.getUsersData().getUserType() = " + user.getUsersData().getUserType());
//            authorities.add(new SimpleGrantedAuthority(UserTyped.STUDENT.getKey()));
//        } else {
//            System.out.println("user.getUsersData().getUserType() = " + user.getUsersData().getUserType());
//            authorities.add(new SimpleGrantedAuthority(UserTyped.WORKER.getKey()));
//        }

        return CustomUserDetails.create(user);
    }

    public UserDetails loadUserByUserAccountId(String accountId) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(accountId).orElseThrow(() -> new NoSuchUserException("해당 유저가 존재하지 않습니다."));
        return CustomUserDetails.create(user);
    }

    @Transactional
    public Long save(userDto.signIn signindto, UsersData InitialUserData) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        signindto.setUserPassword(encoder.encode(signindto.getUserPassword()));

        return userRepository.save(User.builder()
                .accountId(signindto.getAccountId())
                .usersData(InitialUserData)
                .userPassword(signindto.getUserPassword())
                .confirmEmail(signindto.getUserEmail())
                .build()).getId();
    }
}
