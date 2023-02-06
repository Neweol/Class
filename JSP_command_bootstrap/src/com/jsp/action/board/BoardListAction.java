package com.jsp.action.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.command.SearchCriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.dto.ReplyVO;
import com.jsp.service.BoardService;

public class BoardListAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/board/list";
		
		SearchCriteriaCommand command = HttpRequestParameterAdapter.execute(request, SearchCriteriaCommand.class);
		System.out.println(command);
		
		SearchCriteria cri = command.toSearchCriteria();
		System.out.println(cri.getPage());
		
		Map<String, Object> dataMap = boardService.getBoardList(cri);
		List<ReplyVO> replyList = (List<ReplyVO>)dataMap.get("replyList");
		System.out.println(replyList.get(0));
		request.setAttribute("dataMap", dataMap);
		
		return url;
	}

}
