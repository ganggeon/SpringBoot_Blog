package com.gang.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gang.blog.config.auth.principalDetailService;

// 아래 3개 어노테이션 세트는 스프링 시큐리티 필수 
@Configuration //빈등록 (ioc관리)
@EnableWebSecurity // 시큐리티 필터 등록을 이 클래스(SecurityConfig.java)로 한다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크한다
public class SecurityConfig {

	@Autowired
	private principalDetailService principalDetailService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인할때 password를 가로채기를 하는데
	// 해당password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있다
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 것이 좋다)
			  .authorizeRequests()
			    .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			  .and()
			    .formLogin()
			    .loginPage("/auth/loginForm")
			    .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 로그인을 가로챈다.
		       .defaultSuccessUrl("/"); //정상적으로 요청이 완료       
	        return http.build();
	 }
}
