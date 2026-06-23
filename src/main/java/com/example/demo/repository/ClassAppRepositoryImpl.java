package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.ClassAppDTO;
import com.example.demo.entity.ClassApp;

import lombok.extern.log4j.Log4j2;

@Transactional
@Repository
@Log4j2
public class ClassAppRepositoryImpl implements ClassAppRepository {
	private final EntityManager em;

	public ClassAppRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	// 수강 신청 목록
	@Override
	public List<ClassApp> classAppList() {
		return em.createQuery("select ca from ClassApp ca order by classappnum desc", ClassApp.class).getResultList();
	}

	// 수강 신청
	@Override
	public int classAppInsert(ClassAppDTO dto) {
		return em
				.createNativeQuery(
						"insert into classapp (id, clnum, classappstate) value (:id, :clnum, :classappstate)")
				.setParameter("id", dto.getId()).setParameter("clnum", dto.getClnum())
				.setParameter("classappstate", dto.getClassappstate()).executeUpdate();
	}

	// 중복 확인
	@Override
	public boolean isDuplicate(ClassAppDTO dto) {
		// dto에 대한 정보를 조회하고, 결과가 있으면 true 반환
		try {
			Long count = em.createQuery(
					"SELECT COUNT(ca) FROM ClassApp ca WHERE ca.member.id = :id AND ca.classes.clnum = :clnum AND ca.classappstate != '취소'",
					Long.class).setParameter("id", dto.getId()).setParameter("clnum", dto.getClnum()).getSingleResult();

			return count > 0;
		} catch (NoResultException e) {
			return false;
		}
	}

	// 신청완료 건수
	@Override
	public int getCompletedCount(int clnum) {
		return ((Number) em.createQuery(
				"SELECT COUNT(ca) FROM ClassApp ca WHERE ca.classes.clnum = :clnum AND (ca.classappstate = '신청 완료' OR ca.classappstate = '결제 완료')")
				.setParameter("clnum", clnum).getSingleResult()).intValue();
	}

	// 대기건수
	@Override
	public int getWaitingCount(int clnum) {
		Long count = em.createQuery(
				"SELECT COUNT(ca) FROM ClassApp ca WHERE ca.classes.clnum = :clnum AND ca.classappstate = '대기'",
				Long.class).setParameter("clnum", clnum).getSingleResult();
		return count.intValue();
	}

	// cltype 업데이트
	@Override
	public void updateClassType(int clnum, String cltype) {
		em.createQuery("UPDATE Classes c SET c.cltype = :cltype WHERE c.clnum = :clnum").setParameter("cltype", cltype)
				.setParameter("clnum", clnum).executeUpdate();
	}

	// 수강 신청 취소
	public void classAppCancel(Integer classappnum) {
		em.createQuery("UPDATE ClassApp ca SET ca.classappstate = '취소' WHERE ca.classappnum = :classappnum")
				.setParameter("classappnum", classappnum).executeUpdate();
	}

	// 수강 신청 삭제
	@Override
	public void classAppDelete(Integer classappnum) {
		em.createQuery("delete from ClassApp ca where ca.classappnum = :classappnum")
				.setParameter("classappnum", classappnum).executeUpdate();
	}

	// 해당 수강 신청 정보
	@Override
	public ClassApp getClassAppByNum(int classappnum) {
		try {
			return em.createQuery("SELECT ca FROM ClassApp ca WHERE ca.classappnum = :classappnum", ClassApp.class)
					.setParameter("classappnum", classappnum).getSingleResult();
		} catch (NoResultException e) {
			return null; // 해당 번호의 수강 신청이 없는 경우 null을 반환합니다.
		}
	}

	// 대기 순번이 가장 빠른 경우 신청 완료로 변경
	@Override
	public void updateEarliestWaitingToCompleted(int clnum) {
		// 대기 상태인 첫 번째 신청자의 classappnum을 가져옵니다.
		Integer earliestWaitingAppNum = em.createQuery(
				"SELECT ca.classappnum FROM ClassApp ca WHERE ca.classes.clnum = :clnum AND ca.classappstate = '대기' ORDER BY ca.classappnum ASC",
				Integer.class).setParameter("clnum", clnum)
				// 첫 번째 결과만 가져옵니다.
				.setMaxResults(1).getSingleResult();

		// 대기 상태인 첫 번째 신청자의 상태를 '신청 완료'로 변경합니다.
		em.createQuery("UPDATE ClassApp ca SET ca.classappstate = '신청 완료' WHERE ca.classappnum = :classappnum")
				.setParameter("classappnum", earliestWaitingAppNum).executeUpdate();
	}

	// 수강 신청 내역
	@Override
	public List<ClassApp> myClassAppHistory(String id) {
		return em.createQuery("SELECT ca From ClassApp ca WHERE ca.member.id = :id ORDER BY ca.classappnum desc",
				ClassApp.class).setParameter("id", id).getResultList();
	}

	// 결제
	@Override
	public void classAppPayment(Integer classappnum) {
		em.createQuery("UPDATE ClassApp ca SET ca.classappstate = '결제 완료' WHERE ca.classappnum = :classappnum")
				.setParameter("classappnum", classappnum).executeUpdate();
	}

	// 신청 후 3일이내 미결제 취소로 변경
	@Override
	public List<ClassApp> findClassAppsBeforeDate(Timestamp date) {
		log.info(date);
		return em
				.createQuery("SELECT ca FROM ClassApp ca WHERE ca.classappstate = '신청 완료' AND ca.classappdate <= :date",
						ClassApp.class)
				.setParameter("date", date).getResultList();
	}
}
