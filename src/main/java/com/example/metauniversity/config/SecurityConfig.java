package com.example.metauniversity.config;

import com.example.metauniversity.security.CustomUserDetailsService;
import com.example.metauniversity.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                .antMatchers("/**").permitAll() // �늻援щ굹 �젒洹� �뿀�슜
                .antMatchers("/user/**").hasAnyAuthority("STUDENT", "WORKER") // �씡紐낆쓽 �궗�슜�옄留� �븘�땲硫� �뱾�뼱媛� �닔 �엳�뒗 �럹�씠吏�
                .antMatchers("/student/**").hasAuthority("STUDENT") // �븰�깮�슜 �럹�씠吏�
                .antMatchers("/worker/**").hasAuthority("WORKER") // 吏곸썝�슜 �럹�씠吏�
                .and()
                .formLogin() // 7
                .loginPage("/login") // 濡쒓렇�씤 �럹�씠吏� 留곹겕
                .defaultSuccessUrl("/home") // 濡쒓렇�씤 �꽦怨� �썑 由щ떎�씠�젆�듃 二쇱냼
                .and()
                .logout() // 8
                .logoutSuccessUrl("/home") // 濡쒓렇�븘�썐 �꽦怨듭떆 由щ떎�씠�젆�듃 二쇱냼
                .invalidateHttpSession(true)// �꽭�뀡 �궇由ш린
                .and() // 沅뚰븳 �뾾�쓣�뻹 �삤瑜섑럹�씠吏�
                .exceptionHandling().accessDeniedPage("/denied");
    }
}
