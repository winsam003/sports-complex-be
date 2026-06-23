package com.example.demo.entity;

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
import com.example.demo.domain.StaffRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Entity
@Table(name = "Staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {
//	PK
	@Id
	private String stfid;
//	길이, not null
	@Column(length = 100, nullable = false, updatable = false)
	private String stfpassword;
	@Column(length = 20, nullable = false)
	private String stfdmp;
	@Column(length = 10, nullable = false)
	private String stflevel;
	@Column(length = 10, nullable = false)
	private String stfname;
	@Column(nullable = false)
	private Integer stfpnum;
	@Column(length = 20, nullable = false)
	private String stfcode;
	
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<StaffRole> roleList = new ArrayList<>();
	// => JPA는 member_role_list 라는 이름의 Child Table 을 찾음
	//	- JPA 의 DB 표기법 : Entity명_카멜표기의 대문자는_로  
	//	- 없으면 최초실행시 자동 생성시켜줌 (단, application.properties 설정에서 허용시)
	//	- 부모 엔티티인 member 와 Foreign 관계 설정됨. 
	//	- DemoJpaApplicationTests.java Test 코드 참고
	public void addRole(StaffRole staffRole){
		roleList.add(staffRole);
	}

	public void clearRole(){
		roleList.clear();
	}
	
	// => JWT token 발행시 사용됨
	public Map<String, Object> claimList() {
	    Map<String, Object> dataMap = new HashMap<>();
	    dataMap.put("userId", this.stfid);
	    //dataMap.put("pw",this.password);
	    dataMap.put("roleList", this.roleList);

	    return dataMap;
	}
	
}
