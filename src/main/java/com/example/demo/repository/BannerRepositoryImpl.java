package com.example.demo.repository;

import java.util.List;   

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.BannerDTO;
import com.example.demo.entity.Banner;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository 
public class BannerRepositoryImpl implements BannerRepository {
	
	private final EntityManager em;
	
	BannerRepositoryImpl(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<Banner> BannerList() {
		log.info("BannerList Repository 성공");
		
		return em.createQuery("SELECT b FROM Banner b " +
				            "JOIN FETCH b.event " + 
				            "ORDER BY b.bannernum DESC", Banner.class)
				            .getResultList();
	}
	
	@Override
	public int BannerDelete(Integer bannernum) {
		
		log.info("EventDelete Repository 성공");
		String jpql = "DELETE FROM banner where bannernum = :bannernum";
		
		return em.createNativeQuery(jpql, Banner.class)
				 .setParameter("bannernum", bannernum)
				 .executeUpdate();
	}
	
	@Override
	public int BannerInsert(BannerDTO entity) {
		
		log.info("BannerInsert Repository 성공");
		String jpql = "INSERT INTO banner (eventcode, bannerimage) "
					+ "VALUES (:eventcode, :bannerimage)";
		
		Query query = em.createNativeQuery(jpql);
		
		query.setParameter("eventcode", entity.getEventcode());
		query.setParameter("bannerimage", entity.getBannerImage());
		
		return query.executeUpdate();
	}
	
	
	
	
}
