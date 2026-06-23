package com.example.demo.repository;

import java.util.List; 

import com.example.demo.entity.Member;

public interface MemberRepository {
	
	public List<Member> MemberListAll();
	
	public int MemberJoin(Member dto);
	
	public int MemberDelete(String[] deleteId);
	
	public Member MemberOne(String id);
	
	public int mUpdate(Member entity);

	public int mPWChange(Member entity);
	
	public Member mfindID(Member entity);

	Member getWithRoles(String id);
	
	public String findCar(String id);
}
