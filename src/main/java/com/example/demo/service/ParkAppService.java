package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ParkAppDTO;
import com.example.demo.entity.ParkApp;

public interface ParkAppService {
	
	List<ParkApp> parkappList();
	
	List<ParkApp> myParkApp(String id);
	
	int parkApplication(ParkAppDTO dto);
	
	int spaceParking(String spacecode);
	
	int parkappCancel(int parkappnum);
	
	int minusParking(String spacecode);
	
}
