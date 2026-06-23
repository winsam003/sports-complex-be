package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.StaffDTO;
import com.example.demo.entity.Staff;
import com.example.demo.repository.StaffRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

	private final StaffRepositoryImpl repository;

//	전직원 조회
	@Override
	public List<Staff> StaffList() {
		return repository.staffList();
	}

//	직원 등록
	public int staffinsert(StaffDTO dto) {
		return repository.staffinsert(dto);
	}

//	직원 삭제
	@Override
	public void staffdelete(String stfid) {
		repository.staffdelete(stfid);
	}

//  직원 1명 조회
	public Staff StaffOne(String stfid) {
		return repository.StaffOne(stfid);
	}

//	직원 1명 조회인데 권한번호 까지 받아옴
	@Override
	public Staff getWithRoles(String stfid) {
		return repository.getWithRoles(stfid);
	}

	@Override
	public int staffModify(Staff entity) {
		return repository.staffModify(entity);
	}

}
