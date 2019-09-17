package com.metagurukul.metaboard.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import com.metagurukul.metaboard.dao.*;
import com.metagurukul.metaboard.model.notification.*;

public class PostAction extends Action
{

    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
 
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        Notification notification = (Notification) form;
        NotificationDAO notificationDAO = new NotificationDAO();
        String groups[]=request.getParameterValues("groupID");
       
        for(String str:groups)
        {
        
        	if(str.equalsIgnoreCase("6"))
        	{
          		groups=null;
        		groups=new String[1];
        		groups[0]="6";
        		
        		break;
           	}
        	
        }
      
        if( notificationDAO.post(groups,notification) )
            return mapping.findForward(SUCCESS);
        else 
            return mapping.findForward(FAILURE);
    }

}
