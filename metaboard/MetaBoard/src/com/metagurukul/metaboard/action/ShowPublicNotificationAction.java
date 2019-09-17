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

	public class ShowPublicNotificationAction extends Action{
		private final static String SUCCESS = "success";
		private final static String FAILURE = "failure";
		@Override
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			NotificationDAO notificationDao = new NotificationDAO();
			ArrayList<Notification> result=new ArrayList<Notification>();
			int sectionID=Integer.parseInt(request.getParameter("sectionID"));
	        	result=notificationDao.show(sectionID);
	        
			request.setAttribute("result", result);
				
			
			
			return mapping.findForward(SUCCESS);
		}
	
	}
