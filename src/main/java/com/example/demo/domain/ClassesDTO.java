package com.example.demo.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassesDTO {
	private Integer clnum;
	private List<String> cldays;
	private String cltime;
	private String classcode;
	private String clname;
	private LocalDate clrequest;
	private LocalDate clrequestend;
	private Timestamp clstart;
	private Timestamp clend;
	private String clfor;
	private Integer clcount;
	private Integer clwaiting;
	private Integer clprice;
	private String cltype;
	private String teachnum;
}
