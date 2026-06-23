package com.example.demo.service;

import java.util.List; 

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.ParkAppDTO;
import com.example.demo.entity.ParkApp;
import com.example.demo.repository.ParkAppRepositoryImpl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ParkAppServiceImpl implements ParkAppService{
	private final ParkAppRepositoryImpl repository;
	
	@Override
	public List<ParkApp> parkappList() {
		return repository.parkappList();
	}
	
	@Override
	public List<ParkApp> myParkApp(String id) {
		return repository.myParkApp(id);
	}
	
	@Override
	public int parkApplication(ParkAppDTO dto) {
		return repository.parkApplication(dto);
	}
	
	@Override
	public int spaceParking(String spacecode) {
		return repository.spaceParking(spacecode);
	}
	
	@Override
	public int parkappCancel(int parkappnum) {
		return repository.parkappCancel(parkappnum);
	}
	
	@Override
	public int minusParking(String spacecode) {
		return repository.minusParking(spacecode);
	}
	
}
