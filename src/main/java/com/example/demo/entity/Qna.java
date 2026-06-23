package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Qna")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Qna {
//	PK, AUTO Increment
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qanum;
	@Column(length = 20, nullable = false, updatable = false)
	private String qatitle;
	@Column(nullable = false, updatable = false)
//	대용량 데이터
	@Lob
	private String qacontent;
	@Column(length = 10, nullable = false, updatable = false)
	private String qatype;
//	값이 입력되거나 업데이트시 자동으로 시간 입력
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Date qadate;
	@Column(nullable = false, updatable = false)
	private Boolean qaopen;
	@Column(nullable = false, updatable = false)
	private String qapassword;
	@Column(nullable = false, updatable = false)
	private Integer qacount;
	@Column(length = 100, updatable = false)
	private String qafile;
	@Transient
	private MultipartFile qafilef;
	@Column
	private String qareply;
	@CreationTimestamp
	private Date qareplytime;

//	한명의 회원은 여러개의 게시글을 쓸 수 있다
	@ManyToOne
	@JoinColumn(name = "id", updatable = false)
	private Member member;

//	답글을 단 사람이 누구인지
	@ManyToOne
	@JoinColumn(name = "stfid")
	private Staff staff;
}
