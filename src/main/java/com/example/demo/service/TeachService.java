package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TeachDTO;
import com.example.demo.entity.Teach;

public interface TeachService {
	// 강사 목록
	List<Teach> teachList();

	// 강사 상세정보
	Teach teachDetail(Integer teachnum);

	// 강사 등록
	int teachInsert(TeachDTO dto);

	// 강사 업데이트
	int teachUpdate(TeachDTO dto);

	// 강사 삭제
	void teachDelete(Integer teachnum);
}
