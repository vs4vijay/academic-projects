package com.metagurukul.metaboard.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import com.metagurukul.metaboard.dao.*;
import com.metagurukul.metaboard.model.member.*;

public class LogoutAction extends Action
{

	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
 
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
       // Member member = (Member) form;
       // MemberDAO memberDAO = new MemberDAO();
    	
    	request.getSession().invalidate();
    	request.setAttribute("logout", "true");
    	return mapping.findForward("home");
    	
    	/*
        if( memberDAO.register(member))
            return mapping.findForward(SUCCESS);
        else 
            return mapping.findForward(FAILURE);
            */
    }
	
}
