package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ClassAppDTO;
import com.example.demo.entity.ClassApp;

public interface ClassAppService {
	// 수강 신청 목록
	List<ClassApp> classAppList();

	// 수강 신청
	int classAppInsert(ClassAppDTO dto);

	// 중복 확인
	boolean isDuplicateClassApp(ClassAppDTO dto);

	// 신청 완료 건수
	int getCompletedCount(int clnum);

	// 수강 정원
	int getClassesClCount(int clnum);

	// 대기 건수
	int getWaitingCount(int clnum);

	// 대기 정원
	int getClassesClWaiting(int clnum);

	// classes의 cltype 가져오기
	String getClassType(int clnum);

	// cltype 업데이트
	void updateClassType(int clnum, String cltype);

	// 수강 신청 취소
	void classAppCancel(Integer classappnum);

	// 수강 신청 삭제
	void classAppDelete(Integer classappnum);

	// 해당 수강 신청 정보
	ClassApp getClassAppByNum(int classappnum);

	// 대기 순번이 가장 빠른 경우 신청 완료로 변경
	void updateEarliestWaitingToCompleted(int clnum);

	// 수강 신청 내역
	List<ClassApp> myClassAppHistory(String id);

	// 결제
	void classAppPayment(Integer classappnum);

	// 신청 후 3일이내 미결제 취소로 변경
	void updateClassAppStateCancel();
}
