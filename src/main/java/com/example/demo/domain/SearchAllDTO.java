package com.example.demo.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchAllDTO {
	
	// 공지사항, 자주하는질문
	private int notnum;
	private String nottitle;
	private Date notdate;
	private String notdetail;
	private String nottype;
	
	
	// Qna
	private Integer qanum;
	private String qatitle;
	private String qacontent;
	private Date qadate;
	
	
	// 이벤트
	public int eventcode;
	public String eventname;		// 제목
	public String eventdetail;		// 내용
	public String eventtime;		// 작성날짜

}
