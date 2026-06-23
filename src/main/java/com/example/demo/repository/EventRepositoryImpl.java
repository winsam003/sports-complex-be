package com.example.demo.repository;

import java.util.Date; 
import java.util.List;   

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Event;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository 
public class EventRepositoryImpl implements EventRepository {
	
	private final EntityManager em;
	
	EventRepositoryImpl(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<Event> EventList() {
		log.info("EventList Repository 성공");
		return em.createQuery("select e from Event e order by eventcode desc", Event.class).getResultList();
		
	}
	
	@Override
	public int EventDelete(int eventcode) {
		log.info("EventDelete Repository 성공");
		String jpql = "DELETE FROM event where eventcode = :eventcode";
		
		// delete는 
		return em.createNativeQuery(jpql, Event.class)
				 .setParameter("eventcode", eventcode)
				 .executeUpdate();
	}
	
	@Override
	public Event EventDetail(Integer eventcode, String stfid) {
		log.info("EventDetail Repository 성공");
		String jpql = "select e from Event e where eventcode = :eventcode";
		String jpqlU = "update Event "
						+ "set eventcount = eventcount + 1 "
						+ "where eventcode = :eventcode";
		
		log.info("repositoryImpl stfid : "+ stfid);
		
		if(stfid != null) {
			return em.createQuery(jpql, Event.class)
					.setParameter("eventcode", eventcode)
					.getSingleResult();
		} else {
			em.createQuery(jpqlU)
				.setParameter("eventcode", eventcode)
				.executeUpdate();
			return em.createQuery(jpql, Event.class)
					.setParameter("eventcode", eventcode)
					.getSingleResult();
		}
		
	}
	
	@Override
	public int EventInsert(Event Entity) {
		log.info("EventInsert Repository 성공");	
		
		String jpql = "INSERT INTO event (eventname, eventdetail, eventfacility, eventtime, eventfor, eventtype, eventuploadfile, stfid, eventdate, eventcount)"
					+ "VALUES (:eventname, :eventdetail, :eventfacility, :eventtime, :eventfor, :eventtype, :eventuploadfile, :stfid, :eventdate, 0)";
		
		Query query = em.createNativeQuery(jpql);

		query.setParameter("eventname", Entity.getEventname());
		query.setParameter("eventdetail", Entity.getEventdetail());
		query.setParameter("eventfacility", Entity.getEventfacility());
		query.setParameter("eventtime", Entity.getEventtime());
		query.setParameter("eventfor", Entity.getEventfor());
		query.setParameter("eventtype", Entity.getEventtype());
		query.setParameter("eventuploadfile", Entity.getEventuploadfile());
		query.setParameter("stfid", Entity.getStfid());
		query.setParameter("eventdate", new Date(System.currentTimeMillis()));
		
		return query.executeUpdate();
	}
	
	@Override
	public int EventUpdate(Event Entity) {
		
		log.info("EventUpdate Repository 성공");	
		
		
		if(Entity.getEventfilef() != null) {
			String jpql = "UPDATE event "
						+ "SET eventname = :eventname, "
						+ "	   eventdetail = :eventdetail, "
						+ "	   eventfacility = :eventfacility, "
						+ "	   eventtime = :eventtime, "
						+ "	   eventfor = :eventfor, "
						+ "	   eventtype = :eventtype, "
						+ "	   eventuploadfile = :eventuploadfile, "
						+ "	   stfid = :stfid "
						+ "WHERE eventcode = :eventcode";
			
			Query query = em.createNativeQuery(jpql);
			
			log.info("레파지토리 임플임 여기 eventcode : ", Entity.getEventcode());	
			query.setParameter("eventcode", Entity.getEventcode());
			query.setParameter("eventname", Entity.getEventname());
			query.setParameter("eventdetail", Entity.getEventdetail());
			query.setParameter("eventfacility", Entity.getEventfacility());
			query.setParameter("eventtime", Entity.getEventtime());
			query.setParameter("eventfor", Entity.getEventfor());
			query.setParameter("eventtype", Entity.getEventtype());
			query.setParameter("eventuploadfile", Entity.getEventuploadfile());
			query.setParameter("stfid", Entity.getStfid());
			
			
			return query.executeUpdate();
			
		}else {
			String jpql = "UPDATE event "
						+ "SET eventname = :eventname, "
						+ "	   eventdetail = :eventdetail, "
						+ "	   eventfacility = :eventfacility, "
						+ "	   eventtime = :eventtime, "
						+ "	   eventfor = :eventfor, "
						+ "	   eventtype = :eventtype, "
						+ "	   stfid = :stfid "
						+ "WHERE eventcode = :eventcode";
			
			Query query = em.createNativeQuery(jpql);
			
			log.info("레파지토리 임플임 여기 eventcode : ", Entity.getEventcode());	
			query.setParameter("eventcode", Entity.getEventcode());
			query.setParameter("eventname", Entity.getEventname());
			query.setParameter("eventdetail", Entity.getEventdetail());
			query.setParameter("eventfacility", Entity.getEventfacility());
			query.setParameter("eventtime", Entity.getEventtime());
			query.setParameter("eventfor", Entity.getEventfor());
			query.setParameter("eventtype", Entity.getEventtype());
			query.setParameter("stfid", Entity.getStfid());
			
			
			return query.executeUpdate();
			
		}
	}
	
	
	
	@Override
	public List<Event> searchKeyword(String keyword) {
		return em.createQuery("SELECT e FROM Event e WHERE e.eventname LIKE :keyword OR e.eventdetail LIKE :keyword", Event.class)
				.setParameter("keyword", "%" + keyword + "%")
				.getResultList();
	}
	
	
}
