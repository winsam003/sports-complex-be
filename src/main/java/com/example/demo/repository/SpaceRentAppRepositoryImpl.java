package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.SpaceRentAppDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.SpaceRentApp;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Repository
@AllArgsConstructor
public class SpaceRentAppRepositoryImpl implements SpaceRentAppRepository {
	private final EntityManager em;
	
	
	// 대관 신청 시 해당 날짜의 신청가능 여부 조회 
	@Override
	public List<SpaceRentApp> SpaceRentAppList(String searchDate) {
		log.info("SpaceRentAppList Repository 접촉 성공");
		
		return em.createQuery("SELECT a from SpaceRentApp a LEFT JOIN a.spacecode b where a.sprdate = :searchDate", SpaceRentApp.class)
				.setParameter("searchDate", searchDate)
		        .getResultList();
	} // 대관 신청 시 해당 날짜의 신청가능 여부 조회 
	
	
	
	// 대관 신청
	@Override
	public int speaceRentApplication(SpaceRentAppDTO dto) {
		log.info("speaceRentApplication Repository 접촉 성공");
		
		// ** 신청한 시간 불러오기
		LocalDateTime currentDate = LocalDateTime .now();															// 현재시간
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");				
		String formattedDateTime = currentDate.format(formatter);
		// ** 신청한 시간 불러오기
		
		
		
		Member member = em.find(Member.class, dto.getId());
		
		return em.createQuery("UPDATE SpaceRentApp a SET a.appdate = :appdate ,a.appphonenum = :appphonenum, a.numofpeople = :numofpeople, a.id = :id, a.sprstate='확정' WHERE sprnum = :sprnum")
				.setParameter("appphonenum", dto.getAppPhoneNum())
				.setParameter("numofpeople", dto.getNumOfPeople())
				.setParameter("appdate", formattedDateTime)
				.setParameter("id", member)
				.setParameter("sprnum", dto.getSprnum())
				.executeUpdate();
	} // 대관 신청
	
	
	// 매일 10시 금일 기준 3일 이후 신청데이터 자동 입력
	@Override
	public void runDailyTasks() {
		log.info("runDailyTasks Repository 매일 10시 자동 insert 성공");
		
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now().plusDays(3);
        
        // 원하는 형식의 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // 현재 날짜를 원하는 형식으로 변환하여 출력
        String formattedDate = currentDate.format(formatter);
		
		em.createNativeQuery("INSERT INTO spacerentapp (spaceCode, spRDate, spRState, payment, spRState2) VALUES " +
						"('FABK1C','"+formattedDate+" 06:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK2C','"+formattedDate+" 06:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT1C','"+formattedDate+" 06:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT2C','"+formattedDate+" 06:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE1C','"+formattedDate+" 06:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE2C','"+formattedDate+" 06:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK1C','"+formattedDate+" 09:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK2C','"+formattedDate+" 09:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT1C','"+formattedDate+" 09:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT2C','"+formattedDate+" 09:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE1C','"+formattedDate+" 09:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE2C','"+formattedDate+" 09:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK1C','"+formattedDate+" 12:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK2C','"+formattedDate+" 12:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT1C','"+formattedDate+" 12:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT2C','"+formattedDate+" 12:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE1C','"+formattedDate+" 12:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE2C','"+formattedDate+" 12:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK1C','"+formattedDate+" 15:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK2C','"+formattedDate+" 15:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT1C','"+formattedDate+" 15:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT2C','"+formattedDate+" 15:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE1C','"+formattedDate+" 15:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE2C','"+formattedDate+" 15:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK1C','"+formattedDate+" 18:00:00', '접수중', '신용카드', '접수중'), "+
						"('FABK2C','"+formattedDate+" 18:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT1C','"+formattedDate+" 18:00:00', '접수중', '신용카드', '접수중'), "+
						"('FAFT2C','"+formattedDate+" 18:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE1C','"+formattedDate+" 18:00:00', '접수중', '신용카드', '접수중'), "+
						"('FATE2C','"+formattedDate+" 18:00:00', '접수중', '신용카드', '접수중') "
						).executeUpdate(); 
		
		
		em.createNativeQuery("UPDATE spacerentapp SET sprstate = '접수만료', sprstate2 = '접수만료' WHERE DATE(sprdate) = CURDATE()").executeUpdate();
	} // 매일 10시 금일 기준 3일 이후 신청데이터 자동 입력
	
	
	// 대관신청 전체 List
	@Override
	public List<SpaceRentApp> SpaceRentAppAll() {
		log.info("SpaceRentAppAll Repository 접촉 성공");
		
		return em.createQuery("SELECT a from SpaceRentApp a LEFT JOIN a.spacecode b LEFT JOIN a.id c ORDER BY a.sprdate DESC", SpaceRentApp.class)
		        .getResultList();
	} // 대관신청 전체 List
	
	
	// 이미 대관 신청한 유저 체크
	@Override
	public List<SpaceRentApp> AppUserCheck() {
		log.info("AppUserCheck Repository 접촉 성공");
		
		// 현재 날짜 가져오기
        LocalDate currentDate1 = LocalDate.now().plusDays(1);
        LocalDate currentDate2 = LocalDate.now().plusDays(2);
        LocalDate currentDate3 = LocalDate.now().plusDays(3);
        
        // 원하는 형식의 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // 현재 날짜를 원하는 형식으로 변환하여 출력
        String formattedDate1 = currentDate1.format(formatter);
        String formattedDate2 = currentDate2.format(formatter);
        String formattedDate3 = currentDate3.format(formatter);
		
		return em.createQuery("SELECT s FROM SpaceRentApp s WHERE s.sprdate LIKE '%"+formattedDate1+"%' "
															+ "or s.sprdate LIKE '%"+formattedDate2+"%' "
															+ "or s.sprdate LIKE '%"+formattedDate3+"%'", SpaceRentApp.class)
				.getResultList();


	} // 이미 대관 신청한 유저 체크
	
	
	
	// 대관신청 1개만 데이터 가져오기
	@Override
	public SpaceRentApp SpaceRentAppDetail(int sprnum) {
		log.info("SpaceRentAppDetail Repository 접촉 성공");
		return em.createQuery("SELECT s FROM SpaceRentApp s WHERE sprnum = :sprnum", SpaceRentApp.class)
				.setParameter("sprnum", sprnum)
				.getSingleResult();
	} // 대관신청 1개만 데이터 가져오기
	
	
	
	// 대관신청 삭제
	@Override
	public int spaceRentAppDel(int[] checkedUsers) {
		log.info("spaceRentAppDel Repository 접촉 성공");
		
		SpaceRentApp space = new SpaceRentApp();
		
		// 현재 날짜 가져오기
        LocalDate currentDate1 = LocalDate.now().plusDays(1);
        LocalDate currentDate2 = LocalDate.now().plusDays(2);
        LocalDate currentDate3 = LocalDate.now().plusDays(3);
        
        // 원하는 형식의 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // 현재 날짜를 원하는 형식으로 변환하여 출력
        String formattedDate1 = currentDate1.format(formatter);
        String formattedDate2 = currentDate2.format(formatter);
        String formattedDate3 = currentDate3.format(formatter);
        
		for(int i=0 ; i<checkedUsers.length ; i++) {
			space = SpaceRentAppDetail(checkedUsers[i]);
			if(space.getSprdate().contains(formattedDate1) || space.getSprdate().contains(formattedDate2) || space.getSprdate().contains(formattedDate3)) {
				em.createQuery("UPDATE SpaceRentApp a SET a.appdate = '' ,a.appphonenum = '', a.numofpeople = null, a.id = null, a.sprstate='접수중' WHERE sprnum = :sprnum")
				.setParameter("sprnum", checkedUsers[i])
				.executeUpdate();
			}else {
				em.createQuery("UPDATE SpaceRentApp a SET a.appdate = '' ,a.appphonenum = '', a.numofpeople = null, a.id = null, a.sprstate='접수만료' WHERE sprnum = :sprnum")
				.setParameter("sprnum", checkedUsers[i])
				.executeUpdate();
			}			
		}
		return 1;
	} // 대관신청 삭제
	
	
	// 유저 대관신청 기록 불러오기
	@Override
	public List<SpaceRentApp> historyRental(String id) {
		log.info("historyRental Repository 접촉 성공");
		
		Member member = em.find(Member.class, id);
		
		return em.createQuery("SELECT a FROM SpaceRentApp a WHERE id = :id ORDER BY a.sprdate DESC", SpaceRentApp.class)
				.setParameter("id", member)
				.getResultList();
	} // 유저 대관신청 기록 불러오기
	
	
	// 유저 대관신청 기록 삭제
	@Override
	public int historyCancel(int sprnum) {
		log.info("historyCancel Repository 접촉 성공");
		
		SpaceRentApp space = new SpaceRentApp();
		
		// 현재 날짜 가져오기
        LocalDate currentDate1 = LocalDate.now().plusDays(1);
        LocalDate currentDate2 = LocalDate.now().plusDays(2);
        LocalDate currentDate3 = LocalDate.now().plusDays(3);
        
        // 원하는 형식의 포맷터 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // 현재 날짜를 원하는 형식으로 변환하여 출력
        String formattedDate1 = currentDate1.format(formatter);
        String formattedDate2 = currentDate2.format(formatter);
        String formattedDate3 = currentDate3.format(formatter);
        
		space = SpaceRentAppDetail(sprnum);
		
		if(space.getSprdate().contains(formattedDate1) || space.getSprdate().contains(formattedDate2) || space.getSprdate().contains(formattedDate3)) {
			return em.createQuery("UPDATE SpaceRentApp a SET a.appdate = '' ,a.appphonenum = '', a.numofpeople = null, a.id = null, a.sprstate='접수중', a.appdate2 = null ,a.appphonenum2 = null, a.numofpeople2 = null, a.id2 = null, a.sprstate2='접수중' WHERE sprnum = :sprnum")
			.setParameter("sprnum", sprnum)
			.executeUpdate();
		}else {
			return em.createQuery("UPDATE SpaceRentApp a SET a.appdate = '' ,a.appphonenum = '', a.numofpeople = null, a.id = null, a.sprstate='접수만료', a.appdate2 = null ,a.appphonenum2 = null, a.numofpeople2 = null, a.id2 = null, a.sprstate2='접수중' WHERE sprnum = :sprnum")
			.setParameter("sprnum", sprnum)
			.executeUpdate();
		}			
		
	} // 유저 대관신청 기록 삭제
	
	
	// 경기 신청
	@Override
	public int requestBattle(SpaceRentAppDTO dto) {
		
		// ** 신청한 시간 불러오기
		LocalDateTime currentDate = LocalDateTime .now();															// 현재시간
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");							// 현재시간 포맷
		String formattedDateTime = currentDate.format(formatter);													// 포맷된 시간
		// ** 신청한 시간 불러오기
		
		
		Member member = em.find(Member.class, dto.getId2());
		
		return em.createQuery("UPDATE SpaceRentApp a SET a.appdate2 = :appdate2 ,a.appphonenum2 = :appphonenum2, a.numofpeople2 = :numofpeople2, a.id2 = :id2, a.sprstate2='경기신청', a.payment2='신용카드' WHERE sprnum = :sprnum")
				.setParameter("appphonenum2", dto.getAppPhoneNum2())
				.setParameter("numofpeople2", dto.getNumOfPeople2())
				.setParameter("appdate2", formattedDateTime)
				.setParameter("id2", member)
				.setParameter("sprnum", dto.getSprnum())
				.executeUpdate();
	}// 경기 신청
	
	
	
	
	// 경기 수락
	@Override
	public int battleAgree(int sprnum) {
		return em.createQuery("UPDATE SpaceRentApp a SET a.sprstate2='경기수락' WHERE sprnum = :sprnum")
				.setParameter("sprnum", sprnum)
				.executeUpdate();
	} // 경기 수락
	
	
	// 경기 리스트
	@Override
	public List<SpaceRentApp> historyBattle(String id) {
		log.info("historyRental Repository 접촉 성공");
		
		Member member = em.find(Member.class, id);
		
		return em.createQuery("SELECT a FROM SpaceRentApp a WHERE id2 = :id2 ORDER BY a.appdate2 DESC", SpaceRentApp.class)
				.setParameter("id2", member)
				.getResultList();
	} // 경기 리스트
	
	// 경기 취소
	@Override
	public int battleCancel(int sprnum) {
		return em.createQuery("UPDATE SpaceRentApp a SET a.sprstate2 = '접수중', a.id2 = null, payment2 = null, appphonenum2 = null, numofpeople2 = null, appdate2 = null WHERE sprnum = :sprnum")
				.setParameter("sprnum", sprnum)
				.executeUpdate();
	} // 경기 취소
	
}
