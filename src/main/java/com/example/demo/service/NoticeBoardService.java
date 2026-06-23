package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.NoticeBoardDTO;
import com.example.demo.entity.Notice;

public interface NoticeBoardService {

	public List<Notice> NBoardList();
	
	public int noticeDel(Integer[] delBoard);
	
	public int noticeSubmit(Notice entity);
	
	public int noticeModify(Notice entity);
	
	public List<Notice> searchKeyword(String keyword);
	
	public Notice noticeDetail(int notnum);
	
	// 아래부터는 자주하는 질문 ****************************************************************
	
	public List<Notice> fnqList();
}
