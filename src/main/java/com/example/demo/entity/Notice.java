package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="notice")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer notnum;
	private String nottitle;
	private String quest;
	
	@CreationTimestamp
	@Column(nullable = false)
	private Date notdate;
	private String notuploadfile;
	
	@Column(nullable = false)
	private Integer notcount;
	private String notdetail;
	private String nottype;
	
	@JoinColumn(name = "stfid")
	private String stfid;	
	
	@Transient
	private MultipartFile qafilef;
}
