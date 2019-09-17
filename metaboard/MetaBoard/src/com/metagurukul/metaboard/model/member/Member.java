package com.metagurukul.metaboard.model.member;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

public class Member extends ActionForm
{
	int memberID, catID, groupID;
	String name, emailID, contact;
	
	Date dob;
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date date) {
		this.dob = date;
	}
	public int getMemberID() 
	{
		return memberID;
	}
	public void setMemberID(int memberID) 
	{
		this.memberID = memberID;
	}
	public int getCatID() 
	{
		return catID;
	}
	public void setCatID(int catID) 
	{
		this.catID = catID;
	}
	public int getGroupID() 
	{
		return groupID;
	}
	public void setGroupID(int groupID) 
	{
		this.groupID = groupID;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmailID() 
	{
		return emailID;
	}
	public void setEmailID(String emailID) 
	{
		this.emailID = emailID;
	}
	public String getContact() 
	{
		return contact;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors actionErrors = new ActionErrors();
		
		if(name == null || name.trim().equals(""))
		{
			actionErrors.add("name", new ActionMessage("error.name.required"));
		}
		
		if(emailID == null || emailID.trim().equals(""))
		{
			actionErrors.add("emailID", new ActionMessage("error.email.required"));
		}
		
		if(contact == null || contact.trim().equals(""))
		{
			actionErrors.add("contact", new ActionMessage("error.contact.required"));
		}
			
		return actionErrors;
	}
	
}
