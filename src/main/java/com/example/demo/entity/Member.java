package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.domain.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name="member")
public class Member {
	@Id
	private String id;
	private String membercode;
	private String name;
	@Column(updatable = false)
	private String password;
	private String birth;
	private String phonenum;
	private String homenum;
	private String address;
	private String address1;
	private String address2;
	private String email;
	private boolean snsagr;
	private boolean emailagr;
	private String carnum;
	@Column(updatable = false)
	private Date parkuse;
	
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<MemberRole> roleList = new ArrayList<>();
	// => JPA는 member_role_list 라는 이름의 Child Table 을 찾음
	//	- JPA 의 DB 표기법 : Entity명_카멜표기의 대문자는_로  
	//	- 없으면 최초실행시 자동 생성시켜줌 (단, application.properties 설정에서 허용시)
	//	- 부모 엔티티인 member 와 Foreign 관계 설정됨. 
	//	- DemoJpaApplicationTests.java Test 코드 참고
	public void addRole(MemberRole memberRole){
		roleList.add(memberRole);
	}

	public void clearRole(){
		roleList.clear();
	}
	
	// => JWT token 발행시 사용됨
	public Map<String, Object> claimList() {
	    Map<String, Object> dataMap = new HashMap<>();
	    dataMap.put("userId", this.id);
	    //dataMap.put("pw",this.password);
	    dataMap.put("roleList", this.roleList);

	    return dataMap;
	}
	
	// 기본 생성자 추가
    public Member() {}
	
}
