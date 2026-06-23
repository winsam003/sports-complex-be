package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.StaffDTO;
import com.example.demo.entity.Staff;
import com.example.demo.jwtToken.TokenProvider;
import com.example.demo.service.StaffService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(value = "/staff")
public class StaffController {
	StaffService service;
	PasswordEncoder passwordEncoder;
	TokenProvider tokenProvider;

	@GetMapping("/staffList")
	public List<Staff> staffList() {
		return service.StaffList();
	}

	@PostMapping("/staffInsert")
	public ResponseEntity<?> staffinsert(@RequestBody StaffDTO dto) {
		dto.setStfpassword(passwordEncoder.encode(dto.getStfpassword()));
		if (service.staffinsert(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("직원 등록에 성공하셨습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("직원 등록에 실패하였습니다.");
		}
	}

	@GetMapping("/staffDelete")
	public String staffDelete(@RequestParam("stfid") List<String> stfidList) {
		try {
			for (String stfid : stfidList) {
				System.out.println(stfid);
				service.staffdelete(stfid);
			}
		} catch (Exception e) {
			System.out.println(" Delete Excpetion => " + e.toString());
		}
		return "redirect:staff";
	}

	@PostMapping(value = "/staffDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> staffDetail(@RequestBody Staff entity) {
		entity = service.StaffOne(entity.getStfid());
		if (entity != null) {
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(entity);
		}
	}

	@PostMapping(value = "/staffLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> staffLogin(@RequestBody Staff dto) {
		String password = dto.getStfpassword();
		// 해당직원 조회 (권한까지 조회)
		dto = service.getWithRoles(dto.getStfid());

		// 패스워드 비교
		if (dto != null && passwordEncoder.matches(password, dto.getStfpassword())) {

			// 패스워드가 맞다면 토큰 발행
			final String token = tokenProvider.createToken(dto.claimList());
			final StaffDTO staffDTO = StaffDTO.builder().token(token).stfid(dto.getStfid()).stfname(dto.getStfname())
					.roleList(dto.getRoleList()).build();
			log.info("login 성공 token = " + token);
			log.info(staffDTO);
			return ResponseEntity.status(HttpStatus.OK).body(staffDTO);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "로그인에 실패하였습니다. 다시 로그인해주세요.");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response); // 담은 데이터 반환
		}
	}

	// ** staffModify
	@PostMapping(value = "/staffModify", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mUpdate(@RequestBody Staff entity) {
		log.info(entity);
		if (service.staffModify(entity) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("직원정보수정에 성공하였습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("직원정보수정에 실패하였습니다.");
		}
	} // mUpdate

}
