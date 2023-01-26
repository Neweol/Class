package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.NoticeVO;

public interface NoticeDAO {
	
	List<NoticeVO> selectSearchNoticeList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	int selectSearchNoticeListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	NoticeVO selectNoticeByNno(SqlSession session, int nno) throws SQLException;
	
	void increaseViewCount(SqlSession session, int nno) throws SQLException;
	
	int selectNoticeSequenceNextValue(SqlSession session) throws SQLException;
	
	void insertNotice(SqlSession session, NoticeVO notice) throws SQLException;
	
	void updateNotice(SqlSession session, NoticeVO notice) throws SQLException;
	
	void deleteNotice(SqlSession session, int nno) throws SQLException;
	
	
	
}
