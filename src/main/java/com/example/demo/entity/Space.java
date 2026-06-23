package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="space")
public class Space {
	@Id
	public String spacecode;
	public String spacename;
	public int spaceprice;
	public int parkspace;
	public int parking;
	
}