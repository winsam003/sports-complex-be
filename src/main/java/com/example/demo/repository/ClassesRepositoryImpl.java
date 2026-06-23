package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.ClassesDTO;
import com.example.demo.entity.Classes;

import lombok.extern.log4j.Log4j2;

@Log4j2

@Transactional
@Repository
public class ClassesRepositoryImpl implements ClassesRepository {

	private final EntityManager em;

	public ClassesRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	// 강의 목록
	@Override
	public List<Classes> classesList() {
		return em.createQuery("select c from Classes c order by clnum desc", Classes.class).getResultList();
	}

	// 강의 등록
	@Override
	public int classesInsert(ClassesDTO dto) {
		String cldaysString = String.join(",", dto.getCldays());

		return em.createNativeQuery(
				"insert into classes (classcode, cldays, cltime, clname, clrequest, clrequestend, clstart, clend, clfor, clcount, clwaiting, clprice, teachnum)"
						+ "value (:classcode, :cldays, :cltime, :clname, :clrequest, :clrequestend, :clstart, :clend, :clfor, :clcount, :clwaiting, :clprice, :teachnum)")
				.setParameter("classcode", dto.getClasscode()).setParameter("cldays", cldaysString)
				.setParameter("cltime", dto.getCltime()).setParameter("clname", dto.getClname())
				.setParameter("clrequest", dto.getClrequest()).setParameter("clrequestend", dto.getClrequestend())
				.setParameter("clstart", dto.getClstart()).setParameter("clend", dto.getClend())
				.setParameter("clfor", dto.getClfor()).setParameter("clcount", dto.getClcount())
				.setParameter("clwaiting", dto.getClwaiting()).setParameter("clprice", dto.getClprice())
				.setParameter("teachnum", dto.getTeachnum()).executeUpdate();
	}

	// 강의 삭제
	@Override
	public void classesDelete(Integer clnum) {
		em.createQuery("delete from Classes c where c.clnum = :clnum").setParameter("clnum", clnum).executeUpdate();
	}

	// 강의 현황 변경
	@Override
	public void updateClassesType(Integer clnum, String cltype) {
		em.createQuery("update Classes c set c.cltype = :cltype where c.clnum = :clnum").setParameter("cltype", cltype)
				.setParameter("clnum", clnum).executeUpdate();
	}

	// 신청 가능 상태로 변경
	@Override
	public List<Classes> findByCltypeAndClrequest(String cltype, LocalDate clrequest) {
		return em
				.createQuery("select c from Classes c where c.cltype = :cltype and c.clrequest = :clrequest",
						Classes.class)
				.setParameter("cltype", cltype).setParameter("clrequest", clrequest).getResultList();
	}

	// 접수 마감 상태로 변경
	@Override
	public List<Classes> findByCltypeAndClrequestendIn(String cltype1, String cltype2, String cltype3,
			LocalDate clrequestend) {
		return em.createQuery(
				"select c from Classes c where c.cltype in (:cltype1, :cltype2, :cltype3) and c.clrequestend = :clrequestend",
				Classes.class).setParameter("cltype1", cltype1).setParameter("cltype2", cltype2)
				.setParameter("cltype3", cltype3).setParameter("clrequestend", clrequestend).getResultList();
	}

	// 강좌의 cltype 가져오기, classApp에서 사용
	@Override
	public String getClassType(int clnum) {
		return em.createQuery("select c.cltype from Classes c where c.clnum = :clnum", String.class)
				.setParameter("clnum", clnum).getSingleResult();
	}

	// 수강 정원
	@Override
	public int getClassesClCount(int clnum) {
		return ((Number) em.createQuery("SELECT c.clcount FROM Classes c WHERE c.clnum = :clnum")
				.setParameter("clnum", clnum).getSingleResult()).intValue();
	}

	// 대기 정원
	@Override
	public int getClassesClWaiting(int clnum) {
		return ((Number) em.createQuery("SELECT c.clwaiting FROM Classes c WHERE c.clnum = :clnum")
				.setParameter("clnum", clnum).getSingleResult()).intValue();
	}
}
