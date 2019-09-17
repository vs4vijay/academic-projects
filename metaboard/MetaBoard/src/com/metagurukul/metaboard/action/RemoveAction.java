package com.metagurukul.metaboard.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

import com.metagurukul.metaboard.dao.NotificationDAO;
import com.metagurukul.metaboard.model.notification.Notification;
public class RemoveAction extends Action{
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
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
         
         if(notificationDao.updateArchive(1,notificationId))
        	 return mapping.findForward(SUCCESS);
         else 
         {
        	 request.setAttribute("errorMessage", "Error in removing notification");
        	 return mapping.findForward(FAILURE);
         }
         
    }

}
