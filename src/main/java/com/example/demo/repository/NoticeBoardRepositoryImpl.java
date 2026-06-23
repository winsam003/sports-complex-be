package com.example.demo.repository;

import java.util.List;
import java.util.Arrays; 

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.NoticeBoardDTO;
import com.example.demo.entity.Notice;
import javax.persistence.Query;

import lombok.extern.log4j.Log4j2;


@Repository
@Log4j2
public class NoticeBoardRepositoryImpl implements NoticeBoardRepository {
	
	
	private final EntityManager em;
	
	NoticeBoardRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Notice> NBoardList() {
		log.info("NBoardList Repository 접촉 성공");
		
		return em.createQuery("SELECT n FROM Notice n WHERE n.nottype = 'A' ORDER BY n.notnum DESC", Notice.class).getResultList();
	}
	
	@Override
	public int noticeDel(Integer[] delBoard) {
		log.info("noticeDel Repository 접촉 성공");
		log.info("repository delBoard[0]="+delBoard[0]);
		
		int deleteCount = em.createQuery("DELETE FROM Notice WHERE notnum IN (:notnums) ").setParameter("notnums", Arrays.asList(delBoard)).executeUpdate();
		
		return deleteCount;
	}
	
	@Override
	public int noticeSubmit(Notice entity) {
	    log.info("noticeSubmit Repository 접촉 성공");

	    // 네이티브 SQL 쿼리 사용
	    String sql = "INSERT INTO notice (nottitle, quest, notdate, notuploadfile, notcount, notdetail, nottype, stfid) "
	                 + "VALUES (:nottitle, :quest, :notdate, :notuploadfile, :notcount, :notdetail, :nottype, :stfid)";
	    
	    Query query = em.createNativeQuery(sql);
	    
	    query.setParameter("nottitle", entity.getNottitle());
	    query.setParameter("quest", entity.getQuest());
	    query.setParameter("notdate", entity.getNotdate());
	    query.setParameter("notuploadfile", entity.getNotuploadfile());
	    query.setParameter("notcount", entity.getNotcount());
	    query.setParameter("notdetail", entity.getNotdetail());
	    query.setParameter("nottype", entity.getNottype());
	    query.setParameter("stfid", entity.getStfid());
	    query.setParameter("notcount", entity.getNotcount());

	    return query.executeUpdate();
	}
	
	
	@Override
	public int noticeModify(Notice entity) {
		log.info("noticeModify Repository 접촉 성공");
		
		if(entity.getNotuploadfile() != null) {
			String sql = "UPDATE notice SET stfid=:stfid, notuploadfile = :notuploadfile, notdetail = :notdetail where notnum = :notnum";
			
			Query query = em.createNativeQuery(sql);
			query.setParameter("stfid", entity.getStfid());
			query.setParameter("notuploadfile", entity.getNotuploadfile());
			query.setParameter("notdetail", entity.getNotdetail());
			query.setParameter("notnum", entity.getNotnum());
			
			return query.executeUpdate();
		}else {
			String sql = "UPDATE notice SET stfid=:stfid, notdetail = :notdetail where notnum = :notnum";
			
			Query query = em.createNativeQuery(sql);
			query.setParameter("stfid", entity.getStfid());
			query.setParameter("notdetail", entity.getNotdetail());
			query.setParameter("notnum", entity.getNotnum());
			
			return query.executeUpdate();
		}
	}
	
	
	// 검색
	@Override
	public List<Notice> searchKeyword(String keyword) {
		return em.createQuery("SELECT n FROM Notice n WHERE n.nottitle LIKE :keyword OR n.notdetail LIKE :keyword", Notice.class)
				.setParameter("keyword", "%" + keyword + "%")
				.getResultList();
	} // 검색
	
	
	// 1개만 데이터 불러오기
	@Override
	public Notice noticeDetail(int notnum) {
		return em.createQuery("SELECT n FROM Notice n WHERE n.notnum = :notnum ", Notice.class)
		.setParameter("notnum", notnum)
		.getSingleResult();
	} // 1개만 데이터 불러오기
	
	
	// 아래부터는 자주하는 질문 **********************************************************************************
	@Override
	public List<Notice> fnqList() {
		log.info("fnqList Repository 접촉 성공");
		
		return em.createQuery("SELECT n FROM Notice n WHERE n.nottype = 'B' ORDER BY n.notnum DESC", Notice.class).getResultList();
	}
}
