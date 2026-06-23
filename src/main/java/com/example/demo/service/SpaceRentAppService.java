package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.SpaceRentAppDTO;
import com.example.demo.entity.SpaceRentApp;

public interface SpaceRentAppService {
	
	public List<SpaceRentApp> SpaceRentAppList(String searchDate);
	
	public int speaceRentApplication(SpaceRentAppDTO dto);
	
	public void runDailyTasks();

	public List<SpaceRentApp> SpaceRentAppAll();

	public List<SpaceRentApp> AppUserCheck();
	
	public int spaceRentAppDel(int[] checkedUsers);
	
	public List<SpaceRentApp> historyRental(String id);
	
	public int historyCancel(int sprnum);
	
	public int requestBattle(SpaceRentAppDTO dto);
	
	public int battleAgree(int sprnum);
	
	public List<SpaceRentApp> historyBattle(String id);
	
	public int battleCancel(int sprnum);
}
