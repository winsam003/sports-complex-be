package com.example.demo.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class SportsComplexConfig implements WebMvcConfigurer{
	@Bean
	public PasswordEncoder getPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	}
	
	// ** QueryDSL 사용을 위한 설정
	// => EntityManager
	// => JPAQueryFactory를 Bean 등록하여 프로젝트 전역에서 QueryDSL을 작성할 수 있도록 함.
	@PersistenceContext
	// => EntityManager 객체 주입 애너테이션 (@Autowired 로 주입하지 않음)
	// => SpringBoot JPA 에서는 엔티티 매니저 팩토리 관련 부분을 작성하지 않아도 생성 & 주입 해줌
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}
