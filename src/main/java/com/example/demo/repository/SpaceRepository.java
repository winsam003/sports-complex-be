package com.example.demo.repository;

import java.util.List; 

import com.example.demo.entity.Space;

public interface SpaceRepository{
	
	public List<Space> SpaceList();
	
	public int SpaceDelete(String spacecode);
	
	public int SpaceInsert(Space dto);
	
}
