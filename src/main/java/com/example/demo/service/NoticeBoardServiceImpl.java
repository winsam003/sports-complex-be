package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.NoticeBoardDTO;
import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeBoardRepositoryImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
@Transactional
public class NoticeBoardServiceImpl implements NoticeBoardService {

	private final NoticeBoardRepositoryImpl repository;
	
	@Override
	public List<Notice> NBoardList() {
		log.info("Service NBoardList 접촉 성공");
		return repository.NBoardList();
	}
	
	
	@Override
	public int noticeDel(Integer[] delBoard) {
		log.info("Service noticeDel 접촉 성공");
		return repository.noticeDel(delBoard);
	}
	
	@Override
	public int noticeSubmit(Notice entity) {
		log.info("Service noticeSubmit 접촉 성공");
		return repository.noticeSubmit(entity);
	}
	
	@Override
	public int noticeModify(Notice entity) {
		log.info("Service noticeSubmit 접촉 성공");
		return repository.noticeModify(entity);
	}
	
	@Override
	public List<Notice> searchKeyword(String keyword) {
		log.info("Service searchKeyword 접촉 성공");
		return repository.searchKeyword(keyword);
	}
	
	@Override
	public Notice noticeDetail(int notnum) {
		log.info("Service noticeDetail 접촉 성공");
		return repository.noticeDetail(notnum);
	}
	
	
	
	// 아래부터는 자주하는 질문 ************************************************************************************
	@Override
	public List<Notice> fnqList() {
		log.info("Service fnqList 접촉 성공");
		return repository.fnqList();
	}
}

