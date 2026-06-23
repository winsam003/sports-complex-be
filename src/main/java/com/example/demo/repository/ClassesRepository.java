package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.ClassesDTO;
import com.example.demo.entity.Classes;

public interface ClassesRepository {
	// 강의 목록
	List<Classes> classesList();

	// 강의 등록
	int classesInsert(ClassesDTO dto);

	// 강의 삭제
	void classesDelete(Integer clnum);

	// 스케쥴러로 cltype 업데이트
	void updateClassesType(Integer clnum, String cltype);

	// 신청 가능으로 변경
	List<Classes> findByCltypeAndClrequest(String cltype, LocalDate clrequest);

	// 접수 마감으로 변경
	List<Classes> findByCltypeAndClrequestendIn(String cltype1, String cltype2, String cltype3, LocalDate clrequestend);

	// 강좌의 cltype 가져오기, classApp에서 사용
	String getClassType(int clnum);

	// 수강 정원
	int getClassesClCount(int clnum);

	// 대기 정원
	public int getClassesClWaiting(int clnum);
}
