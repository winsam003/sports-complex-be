package com.example.demo.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClassAppDTO {
	private Integer classappnum;
	private Timestamp classappdate;
	private String classappstate;
	private String payment;
	private String id;
	private Integer clnum;
}
