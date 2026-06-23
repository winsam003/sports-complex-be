package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.jwtToken.JwtAuthenticationFilter;

//** Spring_Boot Security  인증 설정 화일
//=> React Project 사용시 인증을 설정에 사용됨

//** @EnableWebSecurity
//=> SpringBoot Auto Configuration @들 중의 하나이며, 손쉽게 Security 설정을 할수있도록해줌.
// 	 그러므로 설정파일을 의미하는 @Configuration 는 없어도 됨

//** SpringBoot Auto Configuration
//=> SpringBoot가 자동으로 설정해줌을 의미하며 이를 지원해주는 다양한 @ 들이 있음.

@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	// ** HttpSecurity
	// => 시큐리티 설정을 위한 오브젝트
	// => 빌더를 이용해서 cors, csrf, httpBasic, session, authorizeRequests 등
	// 다양한 시큐리티관련 설정을 할 수있다.

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// ** Filter 등록
		// => http.addFilterBefore( NewFilter(), BasicAuthenticationFilter.class )
		// : BasicAuthenticationFilter 의 Pre Filter 로 등록
		// => http.addFilterAfter(NewFilter(), BasicAuthenticationFilter.class )
		// : BasicAuthenticationFilter 의 Post Filter 로 등록

		// ** jwtAuthenticationFilter 를 Security filter 에 등록
		// => 아래 68행~ return 구문의 cors() 호출 위치를 보면,
		// 스프링 시큐리티의 Authentication Filter 인증보다 앞서 CorsFilter 검증이 일어나고,
		// 이어서 jwtAuthenticationFilter 가 실행되도록 설정.
		// => 즉, 매 request 마다 CorsFilter 실행후 jwtAuthenticationFilter 실행하도록 설정.
		// 이 순서가 필수는 아니지만 적절하기때문에 이렇게 설정함.
		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);

		// ** http 시큐리티 빌더
		// => disable(): 사용 안함 설정
		// => and() : 체이닝 방식을 사용하여 진행 할 때 사용
		// => csrf(): csrf보호 활성화 호출 후 enable, disable로 설정
		// => cors(): CorsFilter 추가, 추가하지 않을 시 corsConfigurationSource 정의
		// => sessionManagement: 한번에 단일 사용자 인스턴스만 인증되도록 하는 방법
		// => sessionCreationPolicy: 세션 정책
		// .ALWAYS: 항상 세션을 생성
		// .IF_REQUIRED: 필요시 생성(기본)
		// .NEVER: 생성하지 않지만 기존 존재시 사용
		// .STATELESS: 생성하지도 않고, 존재해도 사용하지않음 (JWT 토큰방식을 이용할 경우 사용)
		// => authorizeRequests(): RequestMatcher를 기반으로 엑세스 제한 기능
		// => antMatcher: 제시한 ant 패턴과 일치할 때만 호출 가능
		// => anyRequest ~ authenticated : 어떠한 요청도 인가 받아야함.

		return http.httpBasic().disable() // token을 사용하므로 basic 인증 disable (사용안함) // (HTTP 기본 인증 비활성화 대신 토큰 사용)
				// .formLogin().disable()
				.csrf().disable() // csrf는 현재 사용하지 않으므로 disable // CSRF(Cross-Site Requst Forgery) 비활성화
				.cors().and() // CORS 활성화 => 다른 도메인에서 자원 공유 허용
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and() // 세션을 생성하도록 구성
				// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// => session 기반이 아님을 선언
				.authorizeRequests().antMatchers("/staff/staffInsert").hasRole("ADMIN")
				.antMatchers("/staff/staffModify").hasRole("ADMIN")
				.antMatchers("/spaceRentApp/**", "/parkapp/parkapplication", "/spaceRentApp/requestBattle").hasRole("USER")
				.antMatchers("/", "/event/**", "/member/**", "/qna/**", "/staff/**", "/classes/**", "/notice/**",
						"/space/**", "/SpaceRentAppRepository/**", "/banner/**", "/classApp/**", "/parkapp/**", "/search/**")
				.permitAll()
				// => "/", "/home", "/resources/**", "/uploadImage/**", "/member/**" 등의 경로는 인증
				// 안해도 됨.
				.anyRequest().authenticated().and() // 나머지 모든 요청에 대해서 인증이 필요함을 명시
				// => 위 이외의 모든 경로는 인증해야됨.
				.build(); // 빌드
	} // filterChain

} // class
