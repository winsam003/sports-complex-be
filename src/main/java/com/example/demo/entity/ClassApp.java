package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "classapp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ClassApp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classappnum;
	@CreationTimestamp
	private Timestamp classappdate;
	@Column(length = 10, columnDefinition = "VARCHAR(10) DEFAULT '신청 완료'")
	private String classappstate;
	private String payment;
	@OneToOne
	@JoinColumn(name = "id")
	private Member member;
	@ManyToOne
	@JoinColumn(name = "clnum")
	private Classes classes;
}
