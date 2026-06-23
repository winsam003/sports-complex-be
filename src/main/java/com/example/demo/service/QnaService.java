package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.QnaDTO;
import com.example.demo.entity.Qna;

public interface QnaService {
//	문의 게시글 조회
	List<Qna> qnalist();

//	문의 게시글 상세 페이지
	Qna qnadetail(int qanum);

//	문의 게시글 등록
	int qnainsert(QnaDTO dto);

//	문의 게시글 답변 등록
	int qnareplyinsert(QnaDTO dto);

//	문의 게시글 삭제
	void qnadelete(Integer qanum);
	
//	문의 게시글 전체 검색
	List<Qna> searchKeyword(String keyword);
}
