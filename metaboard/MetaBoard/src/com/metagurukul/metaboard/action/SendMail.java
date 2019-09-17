package com.metagurukul.metaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.metagurukul.metaboard.mail.MailUtil;

public class SendMail extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm mailForm=(DynaActionForm) form;
		MailUtil mail=new MailUtil();
		HttpSession session=request.getSession();
		
			mail.sendMail(mailForm.get("emailID").toString(), mailForm.get("subject").toString(), mailForm.get("message").toString()+" \n From "+session.getAttribute("userName") +"\n"+session.getAttribute("emailID"));
		
		
		
		return mapping.findForward("success");
	}

}
