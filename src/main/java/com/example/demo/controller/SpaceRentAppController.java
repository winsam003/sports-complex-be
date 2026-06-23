package com.example.demo.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.SpaceRentAppDTO;
import com.example.demo.entity.SpaceRentApp;
import com.example.demo.service.SpaceRentAppServiceImpl;

import io.jsonwebtoken.lang.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/spaceRentApp")
@EnableScheduling
public class SpaceRentAppController {
	SpaceRentAppServiceImpl service;
	
	@PostMapping(value="/spaceRentAppAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> SpaceRentAppAll(){
		log.info("SpaceRentAppAll Contoller 접촉 성공");
		List<SpaceRentApp> result = service.SpaceRentAppAll();
		
		if(result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	// 리스트
	@PostMapping(value="/spaceRentApplist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> SpaceRentAppList(@RequestBody SpaceRentAppDTO dto) {
		log.info("SpaceRentAppList Contoller 접촉 성공");
		
		String searchDate = dto.getSprDate();
		List<SpaceRentApp> result = service.SpaceRentAppList(searchDate);
		
		if(result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("출력할 이용시설이 없습니다.");			
		}
	} // 리스트
	 
	
	
	// 대관신청
	@PostMapping(value="/spaceRentApplication", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> spaceRentApplication(@RequestBody SpaceRentAppDTO dto){
		log.info("spaceRentApplication Contoller 접촉 성공");
		
		if(service.speaceRentApplication(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("대관 신청에 성공하였습니다.");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);			
		}
	} // 대관신청
	
	@PostMapping(value="/appUserCheck")
	public ResponseEntity<?> AppUserCheck(@RequestBody String userID){
		log.info("AppUserCheck Contoller 접촉 성공");
		userID = userID.replace("\"", "");
		
		List<SpaceRentApp> result = service.AppUserCheck();
		
		for(SpaceRentApp item : result) {
			if(item.getId() != null && userID.equals(item.getId().getId())) {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("이미 신청한 내역이 있습니다.");				
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(null);				
		
	}
	
	
	@PostMapping(value="/spaceRentAppDel")
	public ResponseEntity<String> spaceRentAppDel(@RequestBody int[] checkedUsers) {
		log.info("spaceRentAppDel Contoller 접촉 성공");
		
		if(service.spaceRentAppDel(checkedUsers) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body("신청 내역 삭제가 완료되었습니다.");				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	@PostMapping(value="/historyRental")
	public ResponseEntity<?> historyRental(@RequestBody SpaceRentAppDTO dto){
		log.info("historyRental Contoller 접촉 성공");
		
		
		String id = dto.getId();
		
		 List<SpaceRentApp> spaceRentApp = service.historyRental(id);
		
		if(spaceRentApp != null && spaceRentApp.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(spaceRentApp);				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	@GetMapping(value="/historyCancel")
	public ResponseEntity<?> historyCancel(@RequestParam("sprnum") int sprnum){
		log.info("historyCancel Contoller 접촉 성공");
		
		if(service.historyCancel(sprnum) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
		
	}
	
	@PostMapping(value="/requestBattle")
	public ResponseEntity<?> requestBattle(@RequestBody SpaceRentAppDTO dto){
		log.info("historyCancel Contoller 접촉 성공");
		
		if(service.requestBattle(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	@GetMapping(value="/battleAgree")
	public ResponseEntity<?> battleAgree(@RequestParam("sprnum") int sprnum){
		log.info("battleAgree Contoller 접촉 성공");
		
		if(service.battleAgree(sprnum) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	@PostMapping(value="/historyBattle")
	public ResponseEntity<?> historyBattle(@RequestBody SpaceRentAppDTO dto){
		log.info("historyRental Contoller 접촉 성공");
		
		
		String id = dto.getId();
		
		 List<SpaceRentApp> spaceRentApp = service.historyBattle(id);
		 log.info(spaceRentApp);
		if(spaceRentApp != null && spaceRentApp.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(spaceRentApp);				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	@GetMapping(value="/battleCancel")
	public ResponseEntity<?> battleCancel(@RequestParam("sprnum") int sprnum){
		log.info("battleCancel Contoller 접촉 성공");
		
		if(service.battleCancel(sprnum) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);				
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
	
	
	// 매일 10시 오늘기준 3일 이후 신청 컬럼생성
	@Scheduled(cron = "0 0 10 * * *")
	public void runDailyTasks() {
		log.info("runDailyTasks Contoller 매일 10시 자동 insert 성공");
		
		service.runDailyTasks();
	} // 매일 10시 오늘기준 3일 이후 신청 컬럼생성
	
	
}
