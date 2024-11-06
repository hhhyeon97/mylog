/*
package com.site.mylog.config;

import com.site.mylog.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// todo : 어렵 ..! 이 파일 내용 공부 필요 !
// 인강이나 책마다 설정 방식이 각기 다름
// 뿌리는 같은 내용인데 코드도 버전마다 달라진 듯 ?!

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 1. 스프링 시큐리티 기능 비활성화
    // ㄴ> 인가, 인증 서비스를 모든 곳에 적용하지는 않을 것이라는 !
    // ㄴ> 일반적으로 정적 리소스 (이미지, html)에 설정한다.
    // ㄴ> 정적 리소스만 스프링 시큐리티 사용을 비활성화 처리
    // static 하위 경로에 있는 리소스를 대상으로 ignoring() 메서드 사용
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers("/static/**");
    }

    // 2. 특정 HTTP 요청에 대한 웹 기반 보안을 구성
    // 이 메서드에서 인증/인가 및 로그인, 로그아웃 관련 설정을 한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests(auth -> auth // 3. 인증, 인가 설정 : 특정 경로에 대한 액세스 설정
                                .requestMatchers( // requestMatchers() : 특정 요청과 일치하는 url에 대한 액세스 설정
                                        new AntPathRequestMatcher("/login"),
                                        new AntPathRequestMatcher("/signup"),
                                        new AntPathRequestMatcher("/user")
                                ).permitAll() // permitAll() : 누구나 접근이 가능하게 설정 ->
                                              // 위 설정한 "/login", "/signup", "/user"로 요청이 오면
                                              // 인증/인가 없이도 접근할 수 있다.
                .anyRequest().authenticated()) // anyRequest() : 위에서 설정한 url 이외의 요청에 대해서 설정
                                               // authenticated() : 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근할 수 있다.
                .formLogin(formLogin -> formLogin // 4. 폼 기반 로그인 설정
                        .loginPage("/login") // loginPage() : 시큐리티 기본 로그인폼 대신 설정할 로그인 페이지 경로 설정
                        .defaultSuccessUrl("/articles", true)  // defaultSuccessUrl() : 로그인이 완료되었을 때 이동할 경로 설정
                ).logout(logout -> logout         // 5. 로그아웃 설정
                        .logoutSuccessUrl("/login") // logoutSuccessUrl() : 로그아웃이 완료되었을 때 이동할 경로 설정
                        .invalidateHttpSession(true) // invalidateHttpSession() : 로그아웃 이후에 세션을 전체 삭제할지 여부를 설정
                )
                .csrf(AbstractHttpConfigurer::disable) // 6. 연습할 땐 잠깐 꺼두기 ! csrf 설정 비활성화
                .build();
    }

    // 7. 인증 관리자 관련 설정
    // 사용자 정보를 가져올 서비스를 재정의하거나, 인증 방법, 예를 들어 LDAP, JDBC 기반 인증 등을 설정할 때 사용
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService)
            throws Exception{
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // 8. 사용자 정보 서비스 설정
                                                        // userDetailsService() : 사용자 정보를 가져올 서비스를 설정
                                                        // 이때 설정하는 서비스 클래스는 반드시 UserDetailsService를 상속받은 클래스여야 한다.
        authProvider.setPasswordEncoder(bCryptPasswordEncoder); // passwordEncoder() : 비밀번호 암호화하기 위한 인코더 설정
        return new ProviderManager(authProvider);
    }

    // 9. 패스워드 인코더를 빈으로 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}*/
