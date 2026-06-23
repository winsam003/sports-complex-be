package com.example.demo.service;

import java.util.List;  

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BannerDTO;
import com.example.demo.entity.Banner;
import com.example.demo.repository.BannerRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class BannerServiceImpl implements BannerService{
	private final BannerRepositoryImpl repository;
	
	@Override
	public List<Banner> BannerList() {
		return repository.BannerList();
	}
		
	@Override
	public int BannerDelete(Integer bannernum) {
		return repository.BannerDelete(bannernum);
	}
	
	@Override
	public int BannerInsert(BannerDTO entity) {
		return repository.BannerInsert(entity);
	}
	
}





