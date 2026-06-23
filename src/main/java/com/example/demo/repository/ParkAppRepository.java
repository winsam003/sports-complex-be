package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.ParkAppDTO;
import com.example.demo.entity.ParkApp;

public interface ParkAppRepository {
	
	public List<ParkApp> parkappList();
	
	public List<ParkApp> myParkApp(String id);

	public int parkApplication(ParkAppDTO dto);
	
	public int spaceParking(String spacecode);
	
	public int parkappCancel(int parkappnum);
	
	public int minusParking(String spacecode);
	
}
