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

public class ShowAction extends Action{
	private final static String SUCCESS = "success";
	private final static String FAILURE = "failure";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NotificationDAO notificationDao = new NotificationDAO();
		ArrayList<Notification> result=new ArrayList<Notification>();
		System.out.println("show action");
		//System.out.println("sectionid  "+request.getParameter("sectionID"));
        int sectionID=0;

		try 
        
        {
			if(request.getParameter("sectionID")!=null)
	        sectionID = Integer.parseInt(request.getParameter("sectionID").trim());

		} 
		catch (Exception e) 
		{
			request.setAttribute("errorMessage", "Wrong SectionID");
       	 	
       	}
        
        String searchBy="";
        try 
        {
            if(request.getParameter("searchBy")!=null)
        	searchBy = request.getParameter("searchBy").trim();
		} 
        catch (Exception e) 
        {
        	request.setAttribute("errorMessage", "SearchBy Parameter is Empty");
       	 	
       	}
        
        
    	String searchVal = "";
    	
    	if(request.getParameter("searchVal")!=null)
    	searchVal=request.getParameter("searchVal");
        
        HttpSession session = request.getSession();
        if(searchBy.equals("title"))
        {
        	result = notificationDao.searchByTitle(session.getAttribute("groupID").toString(), searchVal.trim(), sectionID);
        }
        else if(searchBy.equals("date"))
        {
//        	SqlTimestampConverter t = new SqlTimestampConverter();
//        	java.sql.Timestamp searchTime=(Timestamp) t.convert(String.class,searchVal.trim());
        	result=notificationDao.searchByDate(session.getAttribute("groupID").toString(),searchVal, sectionID);
        }
        else if(searchBy.equals("name"))
        {
        	result=notificationDao.searchByCreator(session.getAttribute("groupID").toString(), searchVal.trim(), sectionID);
        }
        else if(request.getParameter("myPost").equals("true"))
        {
        	
        	result=notificationDao.showMyPost(session.getAttribute("memberID").toString(),sectionID);
        }
        else 
        {
        	result=notificationDao.show(session.getAttribute("groupID").toString(),sectionID);
        }
       
		request.setAttribute("result", result);
			
		System.out.println("searchVal "+searchVal);
		System.out.println("searchBy "+searchBy);
		return mapping.findForward(SUCCESS);
	}

}
