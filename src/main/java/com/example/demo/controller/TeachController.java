package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.TeachDTO;
import com.example.demo.entity.Teach;
import com.example.demo.service.TeachService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/teach")
public class TeachController {
	TeachService service;

//	List
	@GetMapping("/teachList")
	public List<Teach> teachList() {
		return service.teachList();
	}

//	Detail
	@GetMapping("/teachDetail/{teachnum}")
	public Teach teachDetail(@PathVariable("teachnum") Integer teachnum) {
		return service.teachDetail(teachnum);
	}

//	Insert
	@PostMapping("/teachInsert")
	public ResponseEntity<?> teachInsert(@RequestBody TeachDTO dto) {
		if (service.teachInsert(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("강사 등록에 성공하셨습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("강사 등록에 실패하였습니다.");
		}
	}

//	Update
	@PostMapping("/teachUpdate")
	public ResponseEntity<?> teachUpdate(@RequestBody TeachDTO dto) {
		if (service.teachUpdate(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("강사 정보 변경에 성공하셨습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("강사 정보 변경에 실패하였습니다.");
		}
	}

//	Delete
	@GetMapping("/teachDelete")
	public String teachDelete(@RequestParam("teachnum") List<Integer> teachnumList) {
		try {
			for (Integer teachnum : teachnumList) {
				System.out.println(teachnum);
				service.teachDelete(teachnum);
				System.out.println(" 삭제 성공 => " + teachnum);
			}
		} catch (Exception e) {
			System.out.println(" teach Delete Excpetion => " + e.toString());
		}
		return "redirect:teach";
	}
}
