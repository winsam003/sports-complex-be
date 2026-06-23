package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.StaffDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.Staff;

public interface StaffService {
//	전직원 조회
	List<Staff> StaffList();

//	직원 등록
	int staffinsert(StaffDTO dto);

//	직원 삭제
	void staffdelete(String stfid);
	
//	직원 1명 조회
	Staff StaffOne(String stfid);
//	직원 1명 조회인데 권한번호 까지 받아옴
	Staff getWithRoles(String stfid);
	
	int staffModify(Staff entity);
}
