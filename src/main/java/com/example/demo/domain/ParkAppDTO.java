package com.example.demo.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkAppDTO {
	private int parkAppNum;
	private String id;
	private Date parkAppDate;
	private String parkUseDate;
	private String parkAppCancel;
	private String payment;
	private int parkprice;
	private String parkState;
	private String spacecode;
	private String carnum;
	
	
	
}
