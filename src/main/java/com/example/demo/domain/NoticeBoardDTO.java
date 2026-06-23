package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBoardDTO {
	private int notnum;
	private String nottitle;
	private String quest;
	private String notdate;
	private String notuploadfile;
	private int notcount;
	private String notdetail;
	private String nottype;
	private String stfid;	
}
