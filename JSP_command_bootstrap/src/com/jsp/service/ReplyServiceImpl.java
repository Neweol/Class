package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
	
	private SqlSessionFactory sessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	private SqlSession session;
	
	
	@Override
	public Map<String, Object> replyList(int bno, SearchCriteria cri) throws SQLException {
		session = sessionFactory.openSession();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			List<ReplyVO> replyList = replyDAO.selectReplyList(session, bno, cri);
			int count = replyDAO.countReply(session, bno);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);
			
			dataMap.put("replyList", replyList);
			dataMap.put("pageMaker", pageMaker);
			
			return dataMap;
			
		} finally {
			if(session != null) session.close();
		}
	}

	@Override
	public int getReplyListCount(int bno) throws SQLException {
		session = sessionFactory.openSession();
		try {
			
			int count = replyDAO.countReply(session, bno);
			
			return count;
		} finally {
			if(session != null) session.close();
		}
		
	}

	@Override
	public void regist(ReplyVO reply) throws SQLException {
		session = sessionFactory.openSession();
		try {
			replyDAO.insertReply(session, reply);
			
			
		} finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void modify(ReplyVO reply) throws SQLException {
		session = sessionFactory.openSession();
		try {
			replyDAO.updateReply(session, reply);
			
		} finally {
			if(session != null) session.close();
		}
	}

	@Override
	public void delete(int rno) throws SQLException {
		session = sessionFactory.openSession();
		try {
			replyDAO.deleteReply(session, rno);
		} finally {
			if(session != null) session.close();
		}
	}

}
