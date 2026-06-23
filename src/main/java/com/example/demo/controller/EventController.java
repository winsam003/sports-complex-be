package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Event;
import com.example.demo.service.EventServiceImpl;

import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RequestMapping("/event")
@RestController
public class EventController {
	EventServiceImpl service;
	
	// 이벤트 리스트
	@GetMapping(value = "/eventlist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eList() {
		List<Event> result = service.EventList();
		
		if(result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("출력할 이벤트가 없습니다. controller ");
		}
	} //eList
	
	// 이벤트 삭제
	@PostMapping(value = "/eventdelete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eDelete(@RequestBody List<Integer> eventCodes ) {
		log.info(eventCodes);
		
		int deleteCount = 0;
		for( Integer eventCode : eventCodes) {
			int result = service.EventDelete(eventCode);
			if(result > 0) {
				deleteCount++;
			}
		}		
		if(deleteCount > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(eventCodes + "번 이벤트 삭제되었습니다. ");
//			return ResponseEntity.status(HttpStatus.OK).body(deleteCount + "개 이벤트 항목 삭제");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("삭제할 이벤트 게시물이 없습니다.");
		}		
	} //eDelete
	
	// 이벤트 디테일
	@PostMapping(value = "/eventdetail", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> eDetail(@RequestBody Map<String, Object> requestBody){
		log.info(requestBody);
		
		// JSON 타입이라 RequestBody로 받아야한다. 
		// 프론트에서 요청보내는 apiService를 보면 (28줄) content-type 이 JSON 타입이다. 
		// 그러면 아래에 있는 insert는 왜 RequestParam 이 가능한가? 
		//  => else 처리로 content-type이 JSON 타입이 아니라 Multipart타입이기 때문이다. (이건 자바 클래스 중 하나임.)
		
		Integer eventcode = Integer.parseInt((String) requestBody.get("eventcode"));
//	    Integer eventcode = (Integer) requestBody.get("eventcode");
	    String stfid = (String) requestBody.get("stfid");
	    // Map 은 <키, 값>타입. 받는 값은 여러 타입이기 때문에 Object 로 받아주면 다 받을 수 있음. 
	    // 서버에서는 Object 타입을 스트링으로 인식한다. 그래서 스트링에서 받아서 강제 형변환 시켜줄 것. 
	     
	    
		// 게시물을 보는 사람. 
		log.info("controller stfid : " + stfid);
		
		Event result = service.EventDetail(eventcode, stfid);
		log.info("result : " + result);
		
		if(result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("디테일이 없습니다.");
		}
		
		
	} // eDetail
	
	// 이벤트 등록
	@PostMapping(value = "/eventinsert")
	public ResponseEntity<?> eInsert (@RequestParam("eventname") String eventname, 
									  @RequestParam("eventdetail") String eventdetail, 
									  @RequestParam("eventfacility") String eventfacility, 
									  @RequestParam("eventtime") String eventtime, 
									  @RequestParam("eventfor") String eventfor, 
									  @RequestParam("eventtype") String eventtype, 
									  @RequestParam(value = "eventfilef", required = false) MultipartFile eventfilef, 
									  @RequestParam("stfid") String stfid) throws IOException {
		
		log.info("Controller eventinsert");
		
		Event entity = new Event();
		
		entity.setEventname(eventname);
		entity.setEventdetail(eventdetail);
		entity.setEventfacility(eventfacility);
		entity.setEventtime(eventtime);
		entity.setEventfor(eventfor);
		entity.setEventtype(eventtype);
		entity.setEventfilef(eventfilef);
		entity.setStfid(stfid);
		
		// 파일이 있을 경우.
		if (eventfilef != null && !eventfilef.isEmpty()) {
			
			entity.setEventuploadfile(eventfilef.getOriginalFilename());
			
//			String realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\eventBoard\\";
//			String realPath = "/home/ubuntu/server/"; 요러케 폴더를 만들어야 되나
			String realPath = "/home/ubuntu/app/resources/event/";
			
			if(realPath.contains(".TeamSSJ."))
				realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\eventBoard\\";
			else 
//				realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\eventBoard\\";
				realPath = "/home/ubuntu/app/resources/event/";
			
			// 1.1. 해당 위치에 폴더가 존재하지 않다면 만들기
			File file1 = new File(realPath);
			if(!file1.exists()) {
				file1.mkdir();
			}
			
			// 1.2. 저장 할 파일 데이터가 존재한다면 저장 경로에 파일 이름을 붙여주고 파일 복사 (저장)
			MultipartFile uploadfilef = entity.getEventfilef();
			if(uploadfilef != null && !uploadfilef.isEmpty()) {
				String f2 = realPath + uploadfilef.getOriginalFilename();
				File f1 = new File(f2);
				uploadfilef.transferTo(f1);
			}
		}
		
		log.info(entity);
		
		if(service.EventInsert(entity) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("이벤트 등록 완료. 목록으로 이동");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("이벤트 등록 실패");
		}
			
	} // eInsert
	
	// 이벤트 이미지 경로 보내기
	@GetMapping(value = "/eventimages")
	public ResponseEntity<?> getImagePath(@RequestParam String img) throws Exception {
		
		String realPath = "/home/ubuntu/app/resources/event/";
//		String realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\eventBoard\\";
		// String realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\eventBanner\\\\";
		
		FileSystemResource resource = new FileSystemResource(realPath + img);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	} //getImagePath
	
	
	
	
	// 업데이트
	@PostMapping(value = "/eventupdate")
	public ResponseEntity<?> eUpdate(@RequestParam("eventcode") Integer eventcode, 
									 @RequestParam("eventname") String eventname, 
								     @RequestParam("eventdetail") String eventdetail, 
								     @RequestParam("eventfacility") String eventfacility, 
								     @RequestParam("eventtime") String eventtime, 
							   	     @RequestParam("eventfor") String eventfor, 
								     @RequestParam("eventtype") String eventtype, 
								     @RequestParam(value = "eventfilef", required = false) MultipartFile eventfilef, 
								     @RequestParam("stfid") String stfid) throws IOException{
		
		log.info("UPDATE . UPDATE . // Controller eventupdate // UPDATE . UPDATE . ");
		log.info("eventcode : ", eventcode);
		
		Event entity = new Event();
		
		entity.setEventcode(eventcode);
		entity.setEventname(eventname);
		entity.setEventdetail(eventdetail);
		entity.setEventfacility(eventfacility);
		entity.setEventtime(eventtime);
		entity.setEventfor(eventfor);
		entity.setEventtype(eventtype);
		entity.setEventfilef(eventfilef);
		entity.setStfid(stfid);
		
		log.info("entity : ", entity);
		
		// 파일이 있을 경우.
		if (eventfilef != null && !eventfilef.isEmpty()) {
			
			entity.setEventuploadfile(eventfilef.getOriginalFilename());
			
			String realPath = "/home/ubuntu/app/resources/event/";
			
			if(realPath.contains(".TeamSSJ."))
				realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\eventBoard\\";
			else 
				realPath = "/home/ubuntu/app/resources/event/";
			
			// 1.1. 해당 위치에 폴더가 존재하지 않다면 만들기
			File file1 = new File(realPath);
			if(!file1.exists()) {
				file1.mkdir();
			}
			
			// 1.2. 저장 할 파일 데이터가 존재한다면 저장 경로에 파일 이름을 붙여주고 파일 복사 (저장)
			MultipartFile uploadfilef = entity.getEventfilef();
			if(uploadfilef != null && !uploadfilef.isEmpty()) {
				String f2 = realPath + uploadfilef.getOriginalFilename();
				File f1 = new File(f2);
				uploadfilef.transferTo(f1);
			}
		}
		
		
		if(service.EventUpdate(entity) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("이벤트 업데이트 완료. 해당 게시물 디테일로 이동");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("이벤트 업데이트 실패");
		}

		
		
	}
	
	
}





