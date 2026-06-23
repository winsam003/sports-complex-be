package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Classes {
//	PK
	@Id
//	Auto_Increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clnum;
	@Column(length = 25, nullable = false, columnDefinition = "VARCHAR(25) DEFAULT '월'")
	private String cldays;
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(25) DEFAULT '06:00'")
	private String cltime;
	@Column(length = 10, nullable = false)
	private String classcode;
	@Column(length = 40, nullable = false)
	private String clname;
	private LocalDate clrequest;
	private LocalDate clrequestend;
	@Column
	private Timestamp clstart;
	@Column
	private Timestamp clend;
//	강좌대상
	@Column(length = 10, nullable = false)
	private String clfor;
	@Column(nullable = false)
//	강좌 정원
	private Integer clcount;
	@Column(nullable = false)
//	대기 정원
	private Integer clwaiting;
	@Column(nullable = false)
	private Integer clprice;
//	신청현황
	@Column(length = 10, columnDefinition = "VARCHAR(10) DEFAULT '접수 마감'")
	private String cltype;
	@ManyToOne
	@JoinColumn(name = "teachnum")
	private Teach teach;
}