package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.SpaceRentAppDTO;
import com.example.demo.entity.SpaceRentApp;
import com.example.demo.repository.SpaceRentAppRepositoryImpl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class SpaceRentAppServiceImpl implements SpaceRentAppService{
	private final SpaceRentAppRepositoryImpl repository;
	
	@Override
	public List<SpaceRentApp> SpaceRentAppList(String searchDate) {
		log.info("SpaceRentAppList Service 접촉 성공");
		return repository.SpaceRentAppList(searchDate);
	}
	
	@Override
	public int speaceRentApplication(SpaceRentAppDTO dto) {
		log.info("speaceRentApplication Service 접촉 성공");
		return repository.speaceRentApplication(dto);
	}
	
	@Override
	public void runDailyTasks() {
		log.info("runDailyTasks Service 접촉 성공");
		repository.runDailyTasks();
	}
	
	@Override
	public List<SpaceRentApp> SpaceRentAppAll() {
		log.info("SpaceRentAppAll Service 접촉 성공");
		return repository.SpaceRentAppAll();
	}
	
	@Override
	public List<SpaceRentApp> AppUserCheck() {
		log.info("AppUserCheck Service 접촉 성공");
		return repository.AppUserCheck();
	}
	
	@Override
	public int spaceRentAppDel(int[] checkedUsers) {
		log.info("spaceRentAppDel Service 접촉 성공");
		return repository.spaceRentAppDel(checkedUsers);
	}
	
	@Override
	public List<SpaceRentApp> historyRental(String id) {
		log.info("historyRental Service 접촉 성공");
		return repository.historyRental(id);
	}
	
	@Override
	public int historyCancel(int sprnum) {
		log.info("historyCancel Service 접촉 성공");
		return repository.historyCancel(sprnum);
	}
	
	@Override
	public int requestBattle(SpaceRentAppDTO dto) {
		log.info("historyCancel Service 접촉 성공");
		return repository.requestBattle(dto);
	}
	
	@Override
	public int battleAgree(int sprnum) {
		log.info("historyCancel Service 접촉 성공");
		return repository.battleAgree(sprnum);
	}
	
	@Override
	public List<SpaceRentApp> historyBattle(String id) {
		log.info("historyCancel Service 접촉 성공");
		return repository.historyBattle(id);
	}
	
	@Override
	public int battleCancel(int sprnum) {
		log.info("battleCancel Service 접촉 성공");
		return repository.battleCancel(sprnum);
	}
}
