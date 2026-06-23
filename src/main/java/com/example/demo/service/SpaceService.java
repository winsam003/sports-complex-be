package com.example.demo.service;

import java.util.List; 

import com.example.demo.entity.Space;

public interface SpaceService {
	
//	SpaceList
	List<Space> SpaceList();
	
//	SpaceDelete
	int SpaceDelete(String spacecode);
	
//	SpaceInsert
	int SpaceInsert(Space dto);
	
}
