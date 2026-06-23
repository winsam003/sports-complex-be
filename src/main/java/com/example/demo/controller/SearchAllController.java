package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.SearchAllDTO;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Qna;
import com.example.demo.entity.Event;
import com.example.demo.service.EventServiceImpl;
import com.example.demo.service.NoticeBoardServiceImpl;
import com.example.demo.service.QnaServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchAllController {
	NoticeBoardServiceImpl noticeService;
	QnaServiceImpl qnaService;
	EventServiceImpl eventService;
	
	
	@GetMapping("/searchAll")
	public ResponseEntity<?> searchAll(@RequestParam("keyword") String keyword){
		log.info("Contoller searchAll 접촉 성공");
		
		List<SearchAllDTO> dtoList = new ArrayList<>();
		
		List<Notice> NoticeList = noticeService.searchKeyword(keyword);
		List<Qna> QnaList = qnaService.searchKeyword(keyword);
		List<Event> EventList = eventService.searchKeyword(keyword);
		
		
		// Notice 객체의 값들을 각각의 SearchAllDTO 객체에 복사하여 리스트에 추가
		for (Notice notice : NoticeList) {
		    SearchAllDTO dto = new SearchAllDTO();
		    dto.setNotnum(notice.getNotnum()); // 필요한 만큼 필드 복사
		    dto.setNottitle(notice.getNottitle());
		    dto.setNotdate(notice.getNotdate());
		    dto.setNotdetail(notice.getNotdetail());
		    dto.setNottype(notice.getNottype());
		    // 나머지 필드들도 동일하게 복사
		    dtoList.add(dto); // 리스트에 추가
		}
		
		
		// Qna 객체의 값들을 각각의 SearchAllDTO 객체에 복사하여 리스트에 추가
		for (Qna qna : QnaList) {
			SearchAllDTO dto = new SearchAllDTO();
			dto.setQanum(qna.getQanum()); // 필요한 만큼 필드 복사
			dto.setQatitle(qna.getQatitle());
			dto.setQacontent(qna.getQacontent());
			dto.setQadate(qna.getQadate());
			// 나머지 필드들도 동일하게 복사
			dtoList.add(dto); // 리스트에 추가
		}
		
		// Event 객체의 값들을 각각의 SearchAllDTO 객체에 복사하여 리스트에 추가
				for (Event qna : EventList) {
					SearchAllDTO dto = new SearchAllDTO();
					dto.setEventcode(qna.getEventcode()); // 필요한 만큼 필드 복사
					dto.setEventname(qna.getEventname());
					dto.setEventdetail(qna.getEventdetail());
					dto.setEventtime(qna.getEventtime());
					// 나머지 필드들도 동일하게 복사
					dtoList.add(dto); // 리스트에 추가
				}
		
		if(dtoList.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(dtoList);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}
}
