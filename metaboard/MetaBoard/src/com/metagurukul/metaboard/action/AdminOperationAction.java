package com.metagurukul.metaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.metagurukul.metaboard.dao.GroupDAO;
import com.metagurukul.metaboard.dao.SectionDAO;

public class AdminOperationAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm adminOperationForm=(DynaActionForm) form;
		
		String opType=(String) adminOperationForm.get("opType");//request.getParameter("operation");
		String name=(String) adminOperationForm.get("name");//request.getParameter("name");
		GroupDAO groupDAO=new GroupDAO();
		SectionDAO sectionDAO=new SectionDAO();
		
		if(opType.equals("addGroup"))
		{
			if(!groupDAO.exist(name))
			groupDAO.addGroup(name);
		}
		else if(opType.equals("removeGroup"))
		{
			if(groupDAO.exist(name))
				groupDAO.delete(name);
		}
		else if(opType.equals("addSection"))
		{
			if(!sectionDAO.exist(name))
			sectionDAO.addSection(name);
		}else if(opType.equals("removeSection")) 
		{
			if(sectionDAO.exist(name))
			sectionDAO.delete(name);
		}
		else{
			System.out.println("Invalid Choice");
		}
		System.out.println(opType);
		System.out.println(name);
		return mapping.findForward("success");
	}

	
	
}
