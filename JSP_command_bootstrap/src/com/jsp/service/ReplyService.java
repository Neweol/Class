package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.ReplyVO;

public interface ReplyService {
	
	Map<String,Object> replyList(SearchCriteria cri)throws SQLException;
	
	void regist(ReplyVO reply) throws SQLException;
	
	void modify(ReplyVO reply) throws SQLException;
	
	void delete(int rno) throws SQLException;

}
