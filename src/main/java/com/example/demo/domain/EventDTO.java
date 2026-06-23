package com.example.demo.domain; 

import java.sql.Date; 
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDTO {
	public int eventcode;
	public String eventname;		// 제목
	public String eventdetail;		// 내용
	public String eventfacility;
	public String eventtime;		// 작성날짜
	public String eventfor;
	public String eventtype;
	public int eventcount;
	public String eventuploadfile; 
	
	public MultipartFile eventfilef;
	
	public String stfid;
	public Date eventdate;		// 행사일시
	
	
}
