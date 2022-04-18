package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable().and() //h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                .authorizeRequests()//authorizeRequests 가 선언둬어야만 antMatchers 옵션을 사용할 수 있습니다.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()////"/" 등 지정된 URL 들은 permitAll()옵션을 통해 전체 열람 권한을 주었습니다.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())//"/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 했습니다.
                .anyRequest().authenticated()//anyRequest()설정된 값들이외 나머지 url    authenticated(): 나머지 urlㄷ들은 로그인한사용자에게만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/")//로그아웃 기능에 대한 설정의 진입점 / 로그아웃 성공시 "/"주소로 이동
                .and()
                .oauth2Login()// OAuth2Login 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint()//OAuth2 로그인 성공 이후 사용자 정보를 가져올때의 설정을 담당
                .userService(customOAuth2UserService);//소셜 로그인 성공 시 후속조치를 진행할 UserService 인터페이싀의 구현체를 등록
    }
}
