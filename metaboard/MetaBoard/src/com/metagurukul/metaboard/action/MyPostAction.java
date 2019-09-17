package com.metagurukul.metaboard.action;

import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.*;

import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.struts.action.*;

import com.metagurukul.metaboard.dao.NotificationDAO;
import com.metagurukul.metaboard.model.notification.Notification;

public class MyPostAction extends Action{
	private final static String SUCCESS = "success";
	private final static String FAILURE = "failure";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NotificationDAO notificationDao = new NotificationDAO();
		ArrayList<Notification> result=new ArrayList<Notification>();
		
		//System.out.println("sectionid  "+request.getParameter("sectionID"));
        int sectionID;

		try 
        
        {
	        sectionID = Integer.parseInt(request.getParameter("sectionID").trim());

		} 
		catch (Exception e) 
		{
			request.setAttribute("errorMessage", "Wrong SectionID");
       	 	return mapping.findForward(FAILURE);
       	}
		HttpSession session = request.getSession();
        result=notificationDao.showMyPost(session.getAttribute("memberID").toString(),sectionID);
        
		request.setAttribute("result", result);
			
		System.out.println(result);
		
		return mapping.findForward(SUCCESS);
	}

}
