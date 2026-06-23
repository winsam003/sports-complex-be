package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ClassesDTO;
import com.example.demo.entity.Classes;

public interface ClassesService {
	// 강의 목록
	List<Classes> classesList();

	// 강의 등록
	int classesInsert(ClassesDTO dto);

	// 강의 삭제
	void classesDelete(Integer clnum);

	// 강의 상태 접수가능 업데이트
	void updateClassesStatusMorning();

	// 강의 상태 접수마감 업데이트
	void updateClassesStatusEvening();
}
