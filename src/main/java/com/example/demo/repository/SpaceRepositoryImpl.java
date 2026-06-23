package com.example.demo.repository;

import java.util.List; 

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Space;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository 
public class SpaceRepositoryImpl implements SpaceRepository {
	
	private final EntityManager em;
	
	SpaceRepositoryImpl(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<Space> SpaceList() {
		log.info("SpaceList Repository 성공");
		return em.createQuery("select s from Space s", Space.class).getResultList();
	}

	@Override
	public int SpaceDelete(String spaceCode) {
		log.info("SpaceDelete Repository 성공");
		
		String jpql = "DELETE FROM space where spaceCode = :spacecode";
		
		return em.createNativeQuery(jpql, Space.class)
				 .setParameter("spacecode", spaceCode)
				 .executeUpdate();
	}

	@Override
	public int SpaceInsert(Space dto) {
		log.info("SpaceInsert Repository 성공");
		log.info(dto);
		
		String jpql = "INSERT INTO space (spacecode, spacename, spaceprice, parkspace)"
					+ "VALUES (:spacecode, :spacename, :spaceprice, :parkspace)";
		
		Query query = em.createNativeQuery(jpql);
		
		query.setParameter("spacecode", dto.getSpacecode());
		query.setParameter("spacename", dto.getSpacename());
		query.setParameter("spaceprice", dto.getSpaceprice());
		query.setParameter("parkspace", dto.getParkspace());
		
		return query.executeUpdate();
	}
	
	
	
	
}
