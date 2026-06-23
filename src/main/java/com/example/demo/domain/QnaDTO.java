package com.example.demo.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaDTO {
	private Integer qanum;
	private String qatitle;
	private String qacontent;
	private String qatype;
	private Date qadate;
	private Boolean qaopen;
	private String qapassword;
	private Integer qacount;
//	파일명
	private String qafile;
//	Upload_File 정보 전달받기
	private MultipartFile qafilef;
	private String qareply;
	private Date qareplytime;

//	join을 위해
	private String id;
	private String stfid;
}
