package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.TeachDTO;
import com.example.demo.entity.Teach;

@Transactional
@Repository
public class TeachRepositoryImpl implements TeachRepository {
	private final EntityManager em;

	public TeachRepositoryImpl(EntityManager em) {
		this.em = em;
	}

//	강사 조회
	@Override
	public List<Teach> teachList() {
		return em.createQuery("select t from Teach t order by teachnum desc", Teach.class).getResultList();
	}

//	강사 상세 페이지
	@Override
	public Teach teachDetail(Integer teachnum) {
		try {
			return em.createQuery("select t from Teach t where t.teachnum = :teachnum", Teach.class)
					.setParameter("teachnum", teachnum).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

//	강사 등록
	@Override
	public int teachInsert(TeachDTO dto) {
		return em.createNativeQuery(
				"insert into teach (teachcode, teachname, teachbirth, teachphone, teachlicense, teachaccount, teachrdate) "
						+ "values (:teachcode, :teachname, :teachbirth, :teachphone, :teachlicense, :teachaccount, :teachrdate)")
				.setParameter("teachcode", dto.getTeachcode()).setParameter("teachname", dto.getTeachname())
				.setParameter("teachbirth", dto.getTeachbirth()).setParameter("teachphone", dto.getTeachphone())
				.setParameter("teachlicense", dto.getTeachlicense()).setParameter("teachaccount", dto.getTeachaccount())
				.setParameter("teachrdate", dto.getTeachrdate()).executeUpdate();
	}

//	강사 업데이트
	public int teachUpdate(TeachDTO dto) {
		return em.createNativeQuery(
				"Update teach set teachcode = :teachcode, teachphone = :teachphone, teachlicense = :teachlicense, teachaccount = :teachaccount, teachrdate = :teachrdate"
						+ "  where teachnum =:teachnum")
				.setParameter("teachcode", dto.getTeachcode()).setParameter("teachphone", dto.getTeachphone())
				.setParameter("teachlicense", dto.getTeachlicense()).setParameter("teachaccount", dto.getTeachaccount())
				.setParameter("teachrdate", dto.getTeachrdate()).setParameter("teachnum", dto.getTeachnum())
				.executeUpdate();
	}

//	강사 삭제
	@Override
	public void teachDelete(Integer teachnum) {
		em.createQuery("delete from Teach t where t.teachnum = :teachnum").setParameter("teachnum", teachnum)
				.executeUpdate();
	}
}
