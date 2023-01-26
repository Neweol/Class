package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.NoticeDAO;
import com.jsp.dto.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	
	private SqlSessionFactory sqlSessionFactory;
	
	private NoticeDAO noticeDAO;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	private SqlSession session;
	
	@Override
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException {
		session=sqlSessionFactory.openSession();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<NoticeVO> noticeList = noticeDAO.selectSearchNoticeList(session, cri);
			
			int totalCount = noticeDAO.selectSearchNoticeListCount(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(totalCount);
			
			dataMap.put("noticeList", noticeList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
			
		} finally {
			if(session!=null)session.close();
		}
		
	}

	@Override
	public NoticeVO getNotice(int nno) throws SQLException {
		session=sqlSessionFactory.openSession();
		try {
			NoticeVO notice = noticeDAO.selectNoticeByNno(session, nno);
			noticeDAO.increaseViewCount(session, nno);
			
			return notice;
		} finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public NoticeVO getNoticeForModify(int nno) throws SQLException {
		session=sqlSessionFactory.openSession();
		try {
			NoticeVO notice = noticeDAO.selectNoticeByNno(session, nno);
			return notice;
		} finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void regist(NoticeVO notice) throws SQLException {
		session=sqlSessionFactory.openSession();
		try {
			int nno = noticeDAO.selectNoticeSequenceNextValue(session);
			notice.setNno(nno);
			noticeDAO.insertNotice(session, notice);
		} finally {
			if(session!=null)session.close();
		}

	}

	@Override
	public void modify(NoticeVO notice) throws SQLException {
		session=sqlSessionFactory.openSession();
		try {
			noticeDAO.updateNotice(session, notice);
		} finally {
			if(session!=null)session.close();
		}

	}

	@Override
	public void remove(int nno) throws SQLException {
		session=sqlSessionFactory.openSession();
		try {
			noticeDAO.deleteNotice(session, nno);
		} finally {
			if(session!=null)session.close();
		}

	}

}
