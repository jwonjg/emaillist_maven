package com.sds.icto.emaillist.servlet.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.emaillist.dao.EmailListDao;
import com.sds.icto.emaillist.vo.EmailListVo;
import com.sds.icto.web.Action;
import com.sds.icto.web.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	   throws
	   SQLException,
	   ClassNotFoundException,
	   ServletException,
	   IOException {
		
		EmailListDao dao = new EmailListDao();
		List<EmailListVo> list = dao.fetchList();
		request.setAttribute( "list", list );
		WebUtil.forward( "/view/show_emaillist.jsp", request, response);
	}

}
