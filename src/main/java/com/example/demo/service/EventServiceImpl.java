package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class EventServiceImpl implements EventService{
	private final EventRepositoryImpl repository;
	
	@Override
	public List<Event> EventList() {
		return repository.EventList();
	}
	
	@Override
	public int EventDelete(int eventcode) {
		return repository.EventDelete(eventcode);
	}
	
	@Override
	public Event EventDetail(Integer eventCode, String stfid) {
		return repository.EventDetail(eventCode, stfid);
	}
	
	@Override
	public int EventInsert(Event Entity) {
		return repository.EventInsert(Entity);
	}
	
	@Override
	public int EventUpdate(Event Entity) {
		return repository.EventUpdate(Entity);
	}
	
	
	@Override
	public List<Event> searchKeyword(String keyword) {
		return repository.searchKeyword(keyword);
	}
	
//	@Override
//	public void EventCount(Event Entity) {
//		repository.EventCount(Entity);
//	}
	
}





