package com.metagurukul.metaboard.action;


import java.sql.*;
import java.text.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import com.metagurukul.metaboard.dao.NotificationDAO;
import com.metagurukul.metaboard.model.notification.Notification;

public class UpdateAction extends Action
{
	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
         Notification notification = (Notification)form;
         
         NotificationDAO notificationDao = new NotificationDAO();
         
         int notificationId = notification.getNotificationID();
         
         if(notificationDao.update(notificationId, notification))
        	 return mapping.findForward(SUCCESS);
         else 
         {
        	 request.setAttribute("errorMessage", "Error in Updating");
        	 return mapping.findForward(FAILURE);
         }
         
    }
}
