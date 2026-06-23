package com.example.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name="event")
public class Event {
	@Id
	public int eventcode;
	public String eventname;
	public String eventdetail;
	public String eventfacility;
	public String eventtime;
	public String eventfor;
	public String eventtype;
	public int eventcount;
	
	public String eventuploadfile;
	
	@Transient
	public MultipartFile eventfilef;
	
//	@ManyToOne
    @JoinColumn(name = "stfid")
	public String stfid;
    
	@Column(nullable = false)
    public Date eventdate;
	
	
}