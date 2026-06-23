package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Space;
import com.example.demo.service.SpaceServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RequestMapping("/space")
@RestController
public class SpaceController {
	
	SpaceServiceImpl service;
	
	// 리스트
	@GetMapping(value="/spacelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sList() {
		List<Space> result = service.SpaceList();
		if(result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("출력할 이용시설이 없습니다.");			
		}
	}
	
	// 삭제
	@PostMapping(value="/spacedelete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sDelete(@RequestBody List<String> spaceCodes) {
		log.info("deleteTEST");	
		log.info(spaceCodes);
		
		int deleteCount = 0;
		for( String spaceCode : spaceCodes) {
			int result = service.SpaceDelete(spaceCode);
			if(result > 0) {
				deleteCount++;
			}
		}		
		if(deleteCount > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(deleteCount + "개 항목 삭제");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("삭제할 이용시설이 없습니다.");
		}		
	}
	
	// 등록
	@PostMapping (value = "/spaceInsert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sInsert(@RequestBody Space dto){
		if(service.SpaceInsert(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("대관 시설 등록 완료. 목록으로 이동.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("등록 실패. ");			
		}
	} //sInsert
	
	
	
}
