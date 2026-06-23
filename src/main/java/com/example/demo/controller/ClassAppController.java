package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ClassAppDTO;
import com.example.demo.entity.ClassApp;
import com.example.demo.service.ClassAppService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/classApp")
public class ClassAppController {
	ClassAppService service;

	// 수강 신청 목록
	@GetMapping("/classAppList")
	public List<ClassApp> classAppList() {
		return service.classAppList();
	}

	// 수강 신청
	@PostMapping("/classAppInsert")
	public ResponseEntity<?> classAppInsert(@RequestBody ClassAppDTO dto) {
		String message = "";

		// 중복 확인
		if (service.isDuplicateClassApp(dto)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 신청한 강좌입니다.");
		}

		// cltype이 대기 신청인 경우엔 classappstate를 대기로 추가
		String cltype = service.getClassType(dto.getClnum());
		if ("신청 가능".equals(cltype)) {
			dto.setClassappstate("신청 완료");
		} else if ("대기 신청".equals(cltype)) {
			dto.setClassappstate("대기");
		}

		if (service.classAppInsert(dto) > 0) {
			handleClassType(dto.getClnum());

			if ("신청 가능".equals(cltype)) {
				message = "수강 신청에 성공하였습니다.";
			} else if ("대기 신청".equals(cltype)) {
				message = "대기 신청에 성공하였습니다.";
			}
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {

			if ("신청 가능".equals(cltype)) {
				message = "수강 신청에 실패하였습니다.";
			} else if ("대기 신청".equals(cltype)) {
				message = "대기 신청에 실패하였습니다.";
			}
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(message);
		}
	}

	// cltype 변경
	private void handleClassType(int clnum) {
		// 해당 강의(clnum) 신청 건수
		int completedCount = service.getCompletedCount(clnum);
		// 수강 정원
		int classesClCount = service.getClassesClCount(clnum);
		// 해당 강의(clnum) 대기 건수
		int waitingCount = service.getWaitingCount(clnum);
		// 대기 정원
		int classesClWaiting = service.getClassesClWaiting(clnum);

		// cltype이 신청 가능인 경우
		if (completedCount == classesClCount) {
			service.updateClassType(clnum, "대기 신청");
		}
		// cltype이 대기 신청인 경우
		if (waitingCount == classesClWaiting) {
			service.updateClassType(clnum, "대기 마감");
		}
	}

	// 신청 건수, 대기 건수 업데이트
	@PostMapping("/classAppStatusCounts")
	public ResponseEntity<?> classAppStatusCounts(@RequestBody List<Integer> clnumList) {
		Map<String, Object> countMap = new HashMap<>();
		try {
			for (Integer clnum : clnumList) {

				// 해당 강의(clnum) 신청 건수
				int completedCount = service.getCompletedCount(clnum);
				// 해당 강의(clnum) 대기 건수
				int waitingCount = service.getWaitingCount(clnum);

				countMap.put("completed", completedCount);
				countMap.put("waiting", waitingCount);
			}
			// 가져온 카운트 정보를 JSON 형식으로 응답
			return ResponseEntity.ok().body(countMap);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to retrieve classApp status counts");
		}
	}

	// 수강 신청 취소
	@GetMapping("/classAppDelete")
	public void classAppDelete(@RequestParam("classappnum") List<Integer> classappnumList) {
		try {
			for (Integer classappnum : classappnumList) {
				// 수강 신청 정보
				ClassApp classApp = service.getClassAppByNum(classappnum);

				// 신청된 강의 번호
				int clnum = classApp.getClasses().getClnum();

				// 해당 강의의 cltype
				String cltype = service.getClassType(clnum);

				// 수강 신청 취소
				service.classAppCancel(classappnum);

				// 대기 마감상태에서 삭제시 강좌 신청현황 변경
				if (cltype.equals("대기 마감")) {
					service.updateClassType(clnum, "대기 신청");
				}

				// 신청 완료, 결제 완료 인원이 취소시
				// 대기 순번이 가장 빠른 사람 신청 완료로 변경
				if (classApp.getClassappstate().equals("신청 완료") || classApp.getClassappstate().equals("결제 완료")) {
					int waitingCount = service.getWaitingCount(clnum);

					if (waitingCount > 0) {
						service.updateEarliestWaitingToCompleted(clnum);
					}
				}

				// 해당 강의의 수강 신청 인원이 수강 정원보다 적어지면
				// cltype을 수강 신청으로 변경
				int completedCount = service.getCompletedCount(clnum);
				int classesClCount = service.getClassesClCount(clnum);

				if (completedCount < classesClCount) {
					service.updateClassType(clnum, "신청 가능");
				}
			}
		} catch (Exception e) {
			System.out.println(" classes Cancel Excpetion => " + e.toString());
		}
	}

	// 수강 신청 내역
	@PostMapping("/myClassAppHistory")
	public ResponseEntity<?> myClassAppHistory(@RequestBody ClassAppDTO dto) {
		String id = dto.getId();
		List<ClassApp> classApp = service.myClassAppHistory(id);

		if (classApp != null && classApp.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(classApp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
		}
	}

	// 결제
	@GetMapping("/classAppPayment")
	public void classAppPayment(@RequestParam("classappnum") Integer classappnum) {
		try {
			service.classAppPayment(classappnum);
		} catch (Exception e) {
			System.out.println(" classes Payment Excpetion => " + e.toString());
		}
	}
}
