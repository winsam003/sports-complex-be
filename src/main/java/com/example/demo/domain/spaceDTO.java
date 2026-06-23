package com.example.demo.domain; 

import lombok.AllArgsConstructor;  
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class spaceDTO {
	public String spaceCode;
	public String spaceName;
	public int spacePrice;
	public int parkSpace;
	public int parking;
	
}
