package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.StaffDTO;
import com.example.demo.entity.Staff;

import lombok.extern.log4j.Log4j2;

@Transactional
@Repository
@Log4j2
public class StaffRepositoryImpl implements StaffRepository {
	private final EntityManager em;

	public StaffRepositoryImpl(EntityManager em) {
		this.em = em;
	}

//	전직원 조회
	@Override
	public List<Staff> staffList() {
		return em.createQuery("select s from Staff s order by s.stfdmp, s.stflevel desc, s.stfname", Staff.class)
				.getResultList();
	}

//	직원 등록
	@Override
	public int staffinsert(StaffDTO dto) {

		// 쿼리 실행 및 결과 반환

		int result = em
				.createNativeQuery(
						"insert into staff (stfid, stfpassword, stfdmp, stflevel, stfname, stfpnum, stfcode) "
								+ "values (:stfid, :stfpassword, :stfdmp, :stflevel, :stfname, :stfpnum, :stfcode)")
				.setParameter("stfid", dto.getStfid()).setParameter("stfpassword", dto.getStfpassword())
				.setParameter("stfdmp", dto.getStfdmp()).setParameter("stflevel", dto.getStflevel())
				.setParameter("stfname", dto.getStfname()).setParameter("stfpnum", dto.getStfpnum())
				.setParameter("stfcode", dto.getStfcode()).executeUpdate();
		;

		// 회원의 기본 권한 설정
		if (result > 0) {
			if ("팀장".equals(dto.getStflevel())) {
				for (int i = 0; i < 3; i++) {
					// 권한 정보 저장 쿼리 작성
					String roleJpql = "INSERT INTO staff_role_list (staff_stfid, role_list) VALUES (:stfid, :role)";

					// 권한 정보 저장 쿼리 객체 생성
					Query roleQuery = em.createNativeQuery(roleJpql);

					// 회원의 기본 권한 설정 (예: 'USER')
					roleQuery.setParameter("stfid", dto.getStfid());
					roleQuery.setParameter("role", i);

					// 권한 정보 저장 쿼리 실행
					roleQuery.executeUpdate();
				}
			} else if ("사원".equals(dto.getStflevel())) {
				for (int i = 1; i < 3; i++) {
					// 권한 정보 저장 쿼리 작성
					String roleJpql = "INSERT INTO staff_role_list (staff_stfid, role_list) VALUES (:stfid, :role)";

					// 권한 정보 저장 쿼리 객체 생성
					Query roleQuery = em.createNativeQuery(roleJpql);

					// 회원의 기본 권한 설정 (예: 'USER')
					roleQuery.setParameter("stfid", dto.getStfid());
					roleQuery.setParameter("role", i);

					// 권한 정보 저장 쿼리 실행
					roleQuery.executeUpdate();
				}
			}

		}
		return result;

	}

//	직원 삭제
	@Override
	public void staffdelete(String stfid) {
		em.createQuery("delete from Staff s where s.stfid = :stfid").setParameter("stfid", stfid).executeUpdate();
	}

//	직원 1명 조회
	@Override
	public Staff StaffOne(String stfid) {
		log.info(stfid);
		return em.createQuery("select s from Staff s where s.stfid=:stfid", Staff.class).setParameter("stfid", stfid)
				.getSingleResult();
	}

//	직원 1명 조회인데 권한번호 까지 받아옴
	@EntityGraph(attributePaths = { "roleList" })
	@Override
	public Staff getWithRoles(String stfid) {
		return em.createQuery("SELECT s FROM Staff s LEFT JOIN FETCH s.roleList WHERE s.stfid = :stfid", Staff.class)
				.setParameter("stfid", stfid).getSingleResult();
	}

//	직원 정보 수정 
	@Override
	public int staffModify(Staff entity) {

		String sql = "UPDATE staff SET stfdmp = :stfdmp, stflevel = :stflevel, stfname = :stfname, stfpnum = :stfpnum, stfcode = :stfcode WHERE stfid = :stfid";

		Query query = em.createNativeQuery(sql);

		query.setParameter("stfid", entity.getStfid());
		query.setParameter("stfdmp", entity.getStfdmp());
		query.setParameter("stflevel", entity.getStflevel());
		query.setParameter("stfname", entity.getStfname());
		query.setParameter("stfpnum", entity.getStfpnum());
		query.setParameter("stfcode", entity.getStfcode());

		return query.executeUpdate();
	}

}
