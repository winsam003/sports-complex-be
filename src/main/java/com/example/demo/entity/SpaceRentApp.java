package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.Date;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="spacerentapp")
public class SpaceRentApp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sprnum;
	
	@ManyToOne
	@JoinColumn(name = "spacecode", referencedColumnName = "spacecode")
	private Space spacecode;
	
	@Column(length = 30, nullable = false)
	private String sprdate;
	
	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	private Member id;
	
	@Column(length = 11, nullable = true)
	private String appphonenum;
	
	private Integer numofpeople;
	
	@Column(length = 5, nullable = false)
	private String sprstate;
	
	@Column(length = 10, nullable = true)
	private String payment;
	
	@ManyToOne
	@JoinColumn(name = "id2", referencedColumnName = "id")
	private Member id2;
	
	@Column(length = 5, nullable = false)
	private String sprstate2;
	
	@Column(length = 15, nullable = true)
	private String payment2;
	
	@Column(length = 11, nullable = true)
	private String appphonenum2;
	private Integer numofpeople2;
	
	@Column(length = 30, nullable = false)
	private String appdate;
	
	@Column(length = 30, nullable = false)
	private String appdate2;
}
