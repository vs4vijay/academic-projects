package com.metagurukul.metaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.metagurukul.metaboard.dao.NotificationDAO;

public class RestoreAction extends Action{
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		 NotificationDAO notificationDao=new NotificationDAO();
         int notificationId;
         
         try 
         {
             notificationId=Integer.parseInt(request.getParameter("notificationID"));
             request.setAttribute("errorMessage", "Wrong NotificationID");
         } 
         catch (Exception e) 
         {
        	 return mapping.findForward(FAILURE);
         }
         
         if(notificationDao.updateArchive(0,notificationId))
        	 return mapping.findForward(SUCCESS);
         else 
         {
        	 request.setAttribute("errorMessage", "Error in removing notification");
        	 return mapping.findForward(FAILURE);
         }
		
	}
}
