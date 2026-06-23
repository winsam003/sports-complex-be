package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.TeachDTO;
import com.example.demo.entity.Teach;
import com.example.demo.repository.TeachRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeachServiceImpl implements TeachService {
	private final TeachRepositoryImpl repository;

	// 강사 조회
	@Override
	public List<Teach> teachList() {
		return repository.teachList();
	}

//	강사 상세 정보
	@Override
	public Teach teachDetail(Integer teachnum) {
		return repository.teachDetail(teachnum);
	}

//	강사 등록
	@Override
	public int teachInsert(TeachDTO dto) {
		return repository.teachInsert(dto);
	}

//	강사 업데이트
	@Override
	public int teachUpdate(TeachDTO dto) {
		return repository.teachUpdate(dto);
	}

//	강사 삭제
	@Override
	public void teachDelete(Integer teachnum) {
		repository.teachDelete(teachnum);
	}

}
