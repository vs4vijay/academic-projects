package com.metagurukul.metaboard.unittest;

import java.io.File;

import com.metagurukul.metaboard.action.PostAction;

import junit.framework.*;
import servletunit.struts.MockStrutsTestCase;


public class LogoutActionTestCase extends MockStrutsTestCase{
	
	
	  public LogoutActionTestCase(String testName) { super(testName); }

	    public void testLogoutAction() 
	    {
	    	setContextDirectory(new File("WebContent"));
	    	addRequestParameter("m_id","1");
	    	addRequestParameter("name","Ashish");
	    	addRequestParameter("email_id","ashish.sharma@metagurukul.com");
	    	addRequestParameter("contact","9602181180");
	    	addRequestParameter("group_id","1");
	    	addRequestParameter("cat_id","1");
	    	
	        setRequestPathInfo("/logoutAction");
	        actionPerform();
	        verifyForwardPath("/home.jsp");
	    }

}
