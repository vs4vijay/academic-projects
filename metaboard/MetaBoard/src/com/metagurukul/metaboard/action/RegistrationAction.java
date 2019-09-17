package com.metagurukul.metaboard.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import com.metagurukul.metaboard.dao.*;
import com.metagurukul.metaboard.model.member.*;

public class RegistrationAction extends Action
{

	private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
 
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        Member member = (Member) form;
        MemberDAO memberDAO = new MemberDAO();
        HttpSession session=request.getSession();
        if( memberDAO.register(member))
        {
           member=memberDAO.getMemberInfo(member.getEmailID());	
        session.setAttribute("userType", member.getCatID());
    	session.setAttribute("userName", member.getName());
    	session.setAttribute("emailID", member.getEmailID());
        session.setAttribute("groupID", member.getGroupID());
        session.setAttribute("memberID",member.getMemberID());
        	
            return mapping.findForward(SUCCESS);
        }
        else 
        {
            request.setAttribute("errorMessage", "Error in Registration");
            return mapping.findForward(FAILURE);
        }
    }
	
}
