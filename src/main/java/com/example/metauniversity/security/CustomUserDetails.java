package com.example.metauniversity.security;

import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private String userName;
    private String userCode;
    private String accountId;
    private Collection<? extends GrantedAuthority> authorities;
    private User user;

    public CustomUserDetails(String userName, String userCode, String accountId, Collection<? extends GrantedAuthority> authorities, User user) {
        this.userName = userName;
        this.userCode = userCode;
        this.accountId = accountId;
        this.authorities = authorities;
        this.user = user;
    }

    public static CustomUserDetails create(User user) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getUsersData().getUserType().getKey()));

        return new CustomUserDetails(
                user.getUsersData().getUserName(),
                user.getUsersData().getUserCode(),
                user.getAccountId(),
                authorities,
                user);
    }
    
	@Override
    public String getPassword() {
        return this.user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
