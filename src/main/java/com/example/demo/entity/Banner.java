package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int bannernum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventcode" )
	public Event event;
	
	public String bannerimage;
	
	@Transient
	public MultipartFile bannerfilef;
	
	
}