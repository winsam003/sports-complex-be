package com.example.demo.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.ParkAppDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.ParkApp;
import com.example.demo.entity.Space;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Repository
@AllArgsConstructor
@EnableScheduling
public class ParkAppRepositoryImpl implements ParkAppRepository {
	private final EntityManager em;
	
//	public ParkAppRepositoryImpl(EntityManager em) {
//		this.em = em;
//	}
	
	// 주차 신청 내역 리스트
	@Override
	public List<ParkApp> parkappList() {
		log.info("parkappList Repository 성공");
		
		return em.createQuery("select p from ParkApp p order by parkappnum desc", ParkApp.class)
				 .getResultList();
	}
	
	// 내 주차 신청 내역 보기 . 
	@Override
	public List<ParkApp> myParkApp(String id) {
		log.info("myParkApp Repository 성공 / id : " + id);
		
		Member member = em.find(Member.class, id);
		
		String jpql = "SELECT p "
					+ "FROM ParkApp p "
					+ "where p.id = :id "
					+ "ORDER BY parkappnum desc";

		List<ParkApp> result =  em.createQuery(jpql, ParkApp.class)
									.setParameter("id", member)
									.getResultList();
		
		log.info("myParkApp Repository 성공 / result : " + result);
		
		return result;
		
	}
	
	// 주차 신청 
	@Override
	public int parkApplication(ParkAppDTO dto) {
		log.info("parkApplication Repository 성공");
				
		String jpql = "INSERT INTO parkapp ( id, parkUseDate, payment, parkprice, spacecode, carnum, parkappdate, parkstate) "
					+ "VALUES (:id, :parkUseDate, :payment, :parkprice, :spacecode, :carnum, :parkappdate, 'Next') " ;
		
		Query query = em.createNativeQuery(jpql);
		
//		Date useMonth = new Date(System.currentTimeMillis());
		// 사용 년-달 만들기.
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 2;
//		String useMonth = year + "-" + String.format("%02d", month);
        String useMonth = String.format("%d-%02d", year, month);

		
		query.setParameter("id", dto.getId());
		query.setParameter("parkUseDate", useMonth);
		query.setParameter("payment", dto.getPayment());
		query.setParameter("parkprice", dto.getParkprice());
		query.setParameter("spacecode", dto.getSpacecode());
		query.setParameter("parkappdate", currentDate);
		query.setParameter("carnum", dto.getCarnum());
		
		return query.executeUpdate();
		
	}
	
	// 주차자리 하나 더하기. 
	@Override
	public int spaceParking(String spacecode) {
		log.info("주차장 사용하는 자리 하나 더하기.");
		
		String jpql = "UPDATE space SET parking = parking + 1 where spacecode = :spacecode";
				
		return em.createNativeQuery(jpql, Space.class)
				 .setParameter("spacecode", spacecode)
				 .executeUpdate();
	}
	
	// 매월 1일 parkState 변하게 하기. ing는 end, next는 ing로 바꾸게 하기
//	@Scheduled(cron = "0 26 * * * *")
	@Scheduled(cron = "0 0 0 1 * ?")
	@Transactional
	public void updateParkState() {
		
		// parkState가 'ing'인 경우 'end'로 업데이트
		em.createQuery("UPDATE ParkApp p SET p.parkstate = 'end' WHERE p.parkstate = 'ing'").executeUpdate();

		// parkState가 'Next'인 경우 'ing'으로 업데이트
		em.createQuery("UPDATE ParkApp p SET p.parkstate = 'ing' WHERE p.parkstate = 'Next'").executeUpdate();
		
		// space 테이블의 parking자리를 0으로 만들기.
		em.createQuery("UPDATE Space s SET s.parking = 0 WHERE spacecode LIKE 'FAPA%'").executeUpdate();
	}
	
	// 내거 신청 취소하기 . 다음달 예정일 때만 취소 요청 가능
	@Override
	public int parkappCancel(int parkappnum) {
		log.info("주차 신청 취소하기 .");
		
		String jpql = "UPDATE parkapp p "
					+ "SET p.parkstate = 'cancel' "
					+ "WHERE parkappnum = :parkappnum";
		
		Query query = em.createNativeQuery(jpql);
		query.setParameter("parkappnum", parkappnum);
		
		return query.executeUpdate();
		
	}
	
	@Override
	public int minusParking(String spacecode) {
		
		log.info("주차장 사용하는 자리 하나 빼기.");

		String jpql = "UPDATE space "
					+ "SET parking = parking - 1 "
					+ "where spacecode = :spacecode";
		
		return em.createNativeQuery(jpql, Space.class)
				 .setParameter("spacecode", spacecode)
				 .executeUpdate();
		
	}
	
}
