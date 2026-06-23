package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ClassesDTO;
import com.example.demo.entity.Classes;
import com.example.demo.repository.ClassesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableScheduling // 스케쥴러 사용
public class ClassesServiceImpl implements ClassesService {
	private final ClassesRepository repository;

	// 강의 목록
	@Override
	public List<Classes> classesList() {
		return repository.classesList();
	}

	// 강의 등록
	@Override
	public int classesInsert(ClassesDTO dto) {
		return repository.classesInsert(dto);
	}

	// 강의 삭제
	@Override
	public void classesDelete(Integer clnum) {
		repository.classesDelete(clnum);
	}

	// 신청 가능 상태로 변경
	@Override
	// 아침 9시에 실행되도록 변경
	@Scheduled(cron = "0 0 9 * * *")
	@Transactional
	public void updateClassesStatusMorning() {
		LocalDate today = LocalDate.now(); // 현재 날짜 정보만 포함하는 객체 생성

		// 현재 날짜와 clrequest 비교하여 cltype을 신청 가능으로 변경
		List<Classes> classesToUpdate = repository.findByCltypeAndClrequest("접수 마감", today);

		for (Classes c : classesToUpdate) {
			// 강의 신청 시작 날짜
			LocalDate classDate = c.getClrequest();
			// 현재 날짜와 클래스의 신청 시작 날짜가 같다면
			if (classDate.isEqual(today)) {
				c.setCltype("수강 신청");
				repository.updateClassesType(c.getClnum(), "수강 신청");
			}
		}
	}

	// 접수 마감 상태로 변경
	@Override
	@Scheduled(cron = "0 0 22 * * *")
	@Transactional
	public void updateClassesStatusEvening() {
		LocalDate today = LocalDate.now();

		List<Classes> classesToUpdate = repository.findByCltypeAndClrequestendIn("수강 신청", "대기 신청", "대기 마감", today);

		for (Classes c : classesToUpdate) {
			LocalDate classEndDate = c.getClrequestend();

			if (classEndDate.isEqual(today)) {
				c.setCltype("접수 마감");
				repository.updateClassesType(c.getClnum(), "접수 마감");
			}
		}
	}
}
