package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	List<Member> MemberListAll();
	
	int MemberJoin(Member dto);
	
	int MemberDelete(String[] deleteId);
	
	Member MemberOne(String id);
	
	int mUpdate(Member entity);
	
	int mPWChange(Member entity);
	
	Member mfindID(Member entity);
	
	Member getWithRoles(String id);
	
	String findCar(String id);
}
