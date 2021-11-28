package io.security.basicsecurity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().authenticated();

        http
            .formLogin()
//            .loginPage("/loginPage")                // 로그인 페이지
            .defaultSuccessUrl("/")                 // 성공 시 이동 URL
            /*
             * defaultSuccessUrl
             *
             * 스프링 시큐리티는 기본적으로 로그인 성공 시 로그인 성공 직전 거쳐왔던 URL 정보를 기억하고 있다가 로그인 성공 시 해당 URL로 리디렉션
             * 때문에 defaultSuccessUrl 보다 SavedRequest와 RequestCache를 통해 얻은 경로가 우선순위가 더 높다.
             *
             * 하지만, http.defaultSuccessUrl("/", true) 로 설정할 경우 로그인 성공 시 리디렉션 페이지가 무조건 해당 경로로 고정된다.
             */
            .failureUrl("/login")                   // 실패 시 이동 URL
            .usernameParameter("userId")            // username 파라미터명
            .passwordParameter("passwd")            // password 파라미터명
            .loginProcessingUrl("/login_proc")      // 로그인 처리 URL
//            .successHandler(new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                    log.info("authentication : " + authentication.getName());
//                    response.sendRedirect("/");
//                }
//            })
//            .failureHandler(new AuthenticationFailureHandler() {
//                @Override
//                public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                    log.error("exception : " + exception.getMessage());
//                    response.sendRedirect("/login");
//                }
//            })
            .permitAll();


            http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        request.getSession().invalidate();
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                })
                .deleteCookies("remember-me");


            http
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(60 * 60)
                .userDetailsService(userDetailsService);

            http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/expired")
                .and()
                .invalidSessionUrl("/invalid")
                .sessionFixation()
                .changeSessionId()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }
}
