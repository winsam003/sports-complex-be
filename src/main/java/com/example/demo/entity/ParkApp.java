package com.example.demo.entity;

import java.util.Date; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="parkapp")
public class ParkApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int parkappnum;
	
	@JoinColumn(name = "id", referencedColumnName = "id")
	@OneToOne
	private Member id;
	
	@Temporal(TemporalType.DATE)
	private Date parkappdate;
	// 신청한 날짜
	
	private String parkusedate;
	
	private String parkappcancel;
	private String payment;
	private int parkprice;
	private String parkstate;
	
	@JoinColumn(name = "spacecode")
	@OneToOne
	private Space spacecode;
	private String carnum;
	
	
	
}
