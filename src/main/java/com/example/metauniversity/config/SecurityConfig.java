package com.example.metauniversity.config;

import com.example.metauniversity.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").permitAll() // 누구나 접근 허용
            .antMatchers("/user/**").hasAnyAuthority("STUDENT", "WORKER") // 익명의 사용자만 아니면 들어갈 수 있는 페이지
            .antMatchers("/student/**").hasAuthority("STUDENT") // 학생용 페이지
            .antMatchers("/worker/**").hasAuthority("WORKER") // 직원용 페이지
            .and()
            .formLogin() // 7
            .loginPage("/login") // 로그인 페이지 링크
            .defaultSuccessUrl("/home") // 로그인 성공 후 리다이렉트 주소
            .and()
            .logout() // 8
            .logoutSuccessUrl("/home") // 로그아웃 성공시 리다이렉트 주소
            .invalidateHttpSession(true)// 세션 날리기
            .and() // 권한 없을떄 오류페이지
            .exceptionHandling().accessDeniedPage("/denied");
    }
}
