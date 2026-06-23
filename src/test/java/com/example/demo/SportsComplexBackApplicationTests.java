//package com.example.demo;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.demo.domain.MemberRole;
//import com.example.demo.entity.Member;
//import com.example.demo.repository.MemberRepository;
//
//@SpringBootTest
//class SportsComplexBackApplicationTests {
//
//	// @Test
//	void contextLoads() {
//	}
//
//	@Autowired
//	private MemberRepository memberRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Test
//	// => 모든 member 에 Role 부여하기
//	// member_role_list 테이블 완성
//	// => 최초 실행시
//	// -> member_role_list 테이블이 자동 생성됨
//	// -> 이때는 application.properties 의 hibernate.ddl-auto 속성 확인
//	public void addRole() {
//		List<Member> list = memberRepository.MemberListAll();
//		for (Member m : list) {
//			System.out.println(m);
//
//			Member member = Member.builder().id(m.getId()).membercode(m.getMembercode()).password(m.getPassword())
//					.name(m.getName()).birth(m.getBirth()).phonenum(m.getPhonenum()).homenum(m.getHomenum())
//					.address(m.getAddress()).address1(m.getAddress1()).address2(m.getAddress2()).email(m.getEmail())
//					.snsagr(m.isSnsagr()).emailagr(m.isEmailagr()).carnum(m.getCarnum()).parkuse(m.getParkuse())
//					.build();
//			// => builder() 적용해야 Member 엔티티의 roleList가 생성됨.
//			// 즉, roleList 가 notNull 이어야 아래구문의 addRole() 메서드로 담을수있음.
//
//			if (member.getMembercode().endsWith("BJ")) {
//				System.out.println("********************************TEST1");
//				member.addRole(MemberRole.ADMIN);
//				member.addRole(MemberRole.MANAGER);
//				member.addRole(MemberRole.USER);
//			} else if (member.getMembercode().endsWith("SF")) {
//				System.out.println("********************************TEST2");
//				member.addRole(MemberRole.MANAGER);
//				member.addRole(MemberRole.USER);
//			} else if (member.getMembercode().startsWith("ME")) {
//				System.out.println("********************************TEST3");
//				member.addRole(MemberRole.USER);
//			} // if
//
//			// 기존꺼 백업
////			if (member.getMembercode().startsWith("ME")) {
////				member.addRole(MemberRole.ADMIN);
////				member.addRole(MemberRole.MANAGER);
////				member.addRole(MemberRole.USER);
////			}else if  ( member.getId().equals("simsim916") ||
////					member.getId().equals("agr4005") ||
////					member.getId().equals("bamboo7") ||
////					member.getId().equals("kso1") ) {
////				member.addRole(MemberRole.MANAGER);
////				member.addRole(MemberRole.USER);
////			} else {
////				member.addRole(MemberRole.USER);
////			} //if
//			// 기존꺼 백업
//
//			memberRepository.mUpdate(member);
//
//			/*
//			 * DB의 Table member_role_list 를 확인해보면 해당하는 Role의 값이 아래처럼 enum의 ordinal() 값인 int로
//			 * 입력되어있다. enum MemberRole 의 정의된 순서이므로 순서를 변경하면 해당하는 출력값(enum의 name()값) 은 달라진다.
//			 * +------------+-----------+ | member_id | role_list |
//			 * +------------+-----------+ | admin | 0 | -> ADMIN | admin | 1 | -> MANAGER |
//			 * admin | 2 | -> USER
//			 * 
//			 * | bamboo7 | 1 | -> 조장: MANAGER, USER | bamboo7 | 2 |
//			 * 
//			 * | banana | 2 | -> 일반: USER
//			 */
//		} // for
//	} // addRole
//
//}
