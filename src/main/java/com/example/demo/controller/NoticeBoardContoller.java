package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.text.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.demo.domain.NoticeBoardDTO;
import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeBoardServiceImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import java.nio.file.Path;


@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/notice")
public class NoticeBoardContoller {
	NoticeBoardServiceImpl service;
//	private static final String DOWNLOAD_DIR = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\noticeBoard\\";
	private static final String DOWNLOAD_DIR = "/home/ubuntu/app/resources/notice/";
	
	
	// noticeList 공지사항 게시글 list
	@GetMapping(value="/noticeList", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> noticeList(){
		log.info("Contoller noticeList 접촉 성공");
		
		List<Notice> result = service.NBoardList();
		
		if(result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(null);			
		}
	} // noticeList 공지사항 게시글 list
	
	
	
	// noticeDel 공지사항 게시글 삭제
	@PostMapping(value="/noticeDel")
	public ResponseEntity<?> noticeDel(@RequestBody Integer[] delBoard){
		log.info("Contoller noticeDel 접촉 성공");
		
		if(service.noticeDel(delBoard) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제가 완료되었습니다.");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);			
		}
	} // noticeDel 공지사항 게시글 삭제
	
	
	// 날짜
	private Date parseDate(String dateString) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        return dateFormat.parse(dateString);
	    } catch (ParseException e) {
	        // 날짜 형식이 잘못된 경우 처리
	        log.error("Failed to parse date: " + dateString, e);
	        return null; // 또는 적절한 오류 처리
	    }
	}
	
	
	
	// notic1eSubmit 공지사항 등록
	@PostMapping(value="/noticeSubmit")
	public ResponseEntity<?> noticeSubmit( @RequestParam(value = "file", required = false) MultipartFile file,
	                                       @RequestParam("stfid") String stfid,
	                                       @RequestParam("quest") String quest,
	                                       @RequestParam("nottitle") String nottitle,
	                                       @RequestParam("notdetail") String notdetail,
	                                       @RequestParam("notdate") String notdate,
	                                       @RequestParam("notcount") Integer notcount,
										   @RequestParam("nottype") String nottype) throws IOException{
		log.info("Contoller noticeSubmit 접촉 성공");
		
		
		
		Notice entity = new Notice();
		
		entity.setNottitle(nottitle);
		entity.setQuest(quest);
		entity.setNotdate(parseDate(notdate));
		entity.setNotdetail(notdetail);
		entity.setNottype(nottype);
		entity.setStfid(stfid);
		entity.setNotcount(notcount);
		entity.setQafilef(file);
		
		// 파일이 있을 경우.
		if (file != null && !file.isEmpty()) {
			entity.setNotuploadfile(file.getOriginalFilename());
			
			// 1. 배포 전, 배포 후 물리적 위치 저장
//			String realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\noticeBoard\\";
//			String realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex\\public\\img\\";
			String realPath = "/home/ubuntu/app/resources/notice/";
			
			
			// 솔직히 무슨 기준으로 배포 전, 후 를 나눠야할지 모르겠음 일단 같은 폴더로 지정했음
//			if(realPath.contains(".project."))
//				realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\noticeBoard\\";
//			else 
//				realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\noticeBoard\\";
			
			
			// 1.1. 해당 위치에 폴더가 존재하지 않다면 만들기
			File file1 = new File(realPath);
			if(!file1.exists()) {
				file1.mkdir();
			}
			
			// 1.2. 저장 할 파일 데이터가 존재한다면 저장 경로에 파일 이름을 붙여주고 파일 복사 (저장)
			MultipartFile uploadfilef = entity.getQafilef();
			if(uploadfilef != null && !uploadfilef.isEmpty()) {
				String f2 = realPath + uploadfilef.getOriginalFilename();
				File f1 = new File(f2);
				uploadfilef.transferTo(f1);
			}
		}

		
		if(service.noticeSubmit(entity)>0) {
			return ResponseEntity.status(HttpStatus.OK).body("공지사항 등록에 성공하였습니다.");						
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);			
		}
		
	 }// notic1eSubmit 공지사항 등록
	
	
	// 공지사항 수정
	@PostMapping(value="/noticeModify")
	public ResponseEntity<?> noticeModify(@RequestParam(value = "file", required = false) MultipartFile file,
										  @RequestParam("stfid") String stfid,
										  @RequestParam("notdetail") String notdetail,
										  @RequestParam("notnum") int notnum) throws IOException{
		log.info("Contoller noticeModify 접촉 성공");
		
		
		Notice entity = new Notice();
		entity.setStfid(stfid);
		entity.setNotnum(notnum);
		entity.setNotdetail(notdetail);
		
		
		if (file != null && !file.isEmpty()) {
			entity.setNotuploadfile(file.getOriginalFilename());
			
			// 1. 배포 전, 배포 후 물리적 위치 저장
			String realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\noticeBoard\\";
						
						
			// 솔직히 무슨 기준으로 배포 전, 후 를 나눠야할지 모르겠음 일단 같은 폴더로 지정했음
			if(realPath.contains(".Sam."))
				realPath = "E:\\Sam\\project\\Sports-complex\\sports-complex-back\\src\\main\\webapp\\images\\noticeBoard\\";
			else 
				realPath = "/home/ubuntu/app/resources/notice/";
						
						
			// 1.1. 해당 위치에 폴더가 존재하지 않다면 만들기
			File file1 = new File(realPath);
			if(!file1.exists()) {
				file1.mkdir();
			}
						
			// 1.2. 저장 할 파일 데이터가 존재한다면 저장 경로에 파일 이름을 붙여주고 파일 복사 (저장)
			MultipartFile uploadfilef = entity.getQafilef();
			if(uploadfilef != null && !uploadfilef.isEmpty()) {
				String f2 = realPath + uploadfilef.getOriginalFilename();
				File f1 = new File(f2);
				uploadfilef.transferTo(f1);			
				}
			}
		
		if(service.noticeModify(entity)>0) {
			return ResponseEntity.status(HttpStatus.OK).body("공지사항 수정에 성공하였습니다.");						
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);			
		}
	} // 공지사항 수정
	
	
	
	@GetMapping("/downloadFile")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam String fileName) throws IOException{
		
	    Path filePath = Paths.get(DOWNLOAD_DIR, fileName);
	    
	    
	    // 파일 확장자를 추출하여 MIME 타입 결정
	    String mimeType = Files.probeContentType(filePath);
	    if (mimeType == null) {
	        mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // MIME 타입을 알 수 없는 경우 기본값 설정
	    }
	    
	        // 파일의 내용을 바이트 배열로 읽어옴
	    byte[] data = Files.readAllBytes(filePath);
	        // 바이트 배열을 ByteArrayResource 객체로 변환
		
		
	    ByteArrayResource resource = new ByteArrayResource(data);
		
		return ResponseEntity.ok()
                // 파일의 MIME 타입 설정		// MIME(Multipurpose Internet Mail Extensions) => 파일의 형식을 식별 (이미지, 텍스트 ...)
				.contentType(MediaType.parseMediaType(mimeType)) // 모든타입 파일형식 허용
                // 다운로드 시 파일명 지정
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                // 응답에 파일의 내용을 담은 ByteArrayResource 추가
                .body(resource);
	}
	
	
	@GetMapping(value="/noticeDetail")
	public ResponseEntity<?> noticeDetail(@RequestParam("notnum") int notnum){
		log.info("Contoller noticeDetail 접촉 성공");
		
		Notice dto = service.noticeDetail(notnum);
		if(dto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(null);			
		}
	}
	
	
	//아래는 자주하는 질문 *********************************************************************************************************
	
	
	// fnqList 공지사항 게시글 list
	@GetMapping(value="/fnqList", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fnqList(){
		log.info("Contoller fnqList 접촉 성공");
		
		List<Notice> result = service.fnqList();
		
		if(result != null && result.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(null);			
		}
	} // fnqList 공지사항 게시글 list
	
	
}
