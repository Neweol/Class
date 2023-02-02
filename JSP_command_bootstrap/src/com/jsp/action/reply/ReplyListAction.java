package com.jsp.action.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.command.SearchCriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.controller.JSONViewResolver;
import com.jsp.service.ReplyService;

public class ReplyListAction implements Action {
	
	private ReplyService service;
	public void setReplyService(ReplyService service) {
		this.service = service;
	}
	//resolver = 유동적인 데이터플로우를 처리하기 위해 or 자주 사용하는 리절버를 만들기위해.

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		SearchCriteriaCommand criCMD = HttpRequestParameterAdapter.execute(request, SearchCriteriaCommand.class);
		SearchCriteria cri = criCMD.toSearchCriteria();
		Map<String, Object> dataMap = service.replyList(bno, cri);
		
		JSONViewResolver.view(response, dataMap);
		
		return url;
	}

}
