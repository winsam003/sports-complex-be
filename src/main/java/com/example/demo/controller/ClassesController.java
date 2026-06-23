package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ClassesDTO;
import com.example.demo.entity.Classes;
import com.example.demo.service.ClassesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/classes")
public class ClassesController {
	ClassesService service;

//	List
	@GetMapping("/classesList")
	public List<Classes> classesList() {
		return service.classesList();
	}

// Insert
	@PostMapping("/classesInsert")
	public ResponseEntity<?> classesInsert(@RequestBody ClassesDTO dto) {
		if (service.classesInsert(dto) > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("신규 강좌 등록에 성공하셨습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("신규 강좌 등록에 실패하였습니다.");
		}
	}

//	Delete
	@GetMapping("/classesDelete")
	public String classesDelete(@RequestParam("clnum") List<Integer> clnumList) {
		try {
			for (Integer clnum : clnumList) {
				System.out.println(clnum);
				service.classesDelete(clnum);
				System.out.println(" 삭제 성공 => " + clnum);
			}
		} catch (Exception e) {
			System.out.println(" classes Delete Excpetion => " + e.toString());
		}
		return "redirect:classes";
	}
}
