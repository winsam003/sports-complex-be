package com.example.demo.domain;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeachDTO {
	private Integer teachnum;
	private String teachcode;
	private String teachname;
	private Date teachbirth;
	private String teachphone;
	private String teachlicense;
	private String teachaccount;
	private Timestamp teachrdate;
}
