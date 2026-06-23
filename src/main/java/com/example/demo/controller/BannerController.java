package com.example.demo.controller;

import java.io.File; 
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BannerDTO;
import com.example.demo.domain.EventDTO;
import com.example.demo.entity.Banner;
import com.example.demo.entity.Event;
import com.example.demo.service.BannerServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RequestMapping("/banner")
@RestController
public class BannerController {
	BannerServiceImpl service;
	
	// 리스트
	@GetMapping(value = "/bannerlist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bList() {
		log.info("배너리스트");
		List<Banner> banner = service.BannerList();
//		List<BannerDTO> banner = service.BannerList();
		log.info("리스트 뽑았다", banner);
		if(banner != null && banner.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(banner);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("출력할 배너가 없습니다. ");
		}
 	} // bList
	
	// 삭제
	@PostMapping(value = "/bannerdelete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bDelete(@RequestBody List<Integer> bannerNums ) {
		log.info(bannerNums);
		
		int deleteCount = 0;
		for(Integer bannerNum : bannerNums) {
			int delete = service.BannerDelete(bannerNum);
			if(delete > 0) {
				deleteCount++;
			}
		}
		if(deleteCount > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(bannerNums + "번 배너 삭제 ");
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("삭제할 배너가 없습니다.");
		}
	} // bDelete

	// 등록 
	@PostMapping(value = "/bannerinsert")
	public ResponseEntity<?> bInsert (@RequestParam("eventcode") int eventcode, 
									  @RequestParam("bannerfilef") MultipartFile bannerfilef)
									  throws IOException {
		log.info("Controller bannerinsert");
		log.info(eventcode);
		
		BannerDTO dto = new BannerDTO();
		
		dto.setEventcode(eventcode);
		dto.setBannerfilef(bannerfilef);
		
		if(bannerfilef != null && !bannerfilef.isEmpty()) {
			dto.setBannerImage(bannerfilef.getOriginalFilename());
			
//			String realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\mainBanner\\";
			String realPath = "/home/ubuntu/app/resources/banner/";
						
			// 1.1. 해당 위치에 폴더가 존재하지 않다면 만들기
			File file1 = new File(realPath);
			if(!file1.exists()) {
				file1.mkdir();
			}
			
			// 1.2. 저장 할 파일 데이터가 존재한다면 저장 경로에 파일 이름을 붙여주고 파일 복사 (저장)
			MultipartFile uploadfilef = dto.getBannerfilef();
			if(uploadfilef != null && !uploadfilef.isEmpty()) {
				String f2 = realPath + uploadfilef.getOriginalFilename();
				File f1 = new File(f2);
				uploadfilef.transferTo(f1);
//				log.info("애프투다 : " + f2);
			}
			
			
		}
		
		
		if(service.BannerInsert(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("배너 등록 완료");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("배너 등록 실패");
		}
		
	}
	
	@GetMapping(value = "/bannerimages")
	public ResponseEntity<?> getImagePath(@RequestParam String img) throws Exception {
//		String realPath = "C:\\jgj\\TeamSSJ\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\mainBanner\\";
//		String realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\mainBanner\\";
		String realPath = "/home/ubuntu/app/resources/banner/";
		
		FileSystemResource resource = new FileSystemResource(realPath + img);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
		
	}
	
	
	
	
}





