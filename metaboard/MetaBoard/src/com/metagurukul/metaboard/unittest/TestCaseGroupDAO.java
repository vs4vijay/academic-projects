package com.metagurukul.metaboard.unittest;

import java.util.ArrayList;

import org.junit.Test;

import com.metagurukul.metaboard.dao.GroupDAO;
import com.metagurukul.metaboard.model.group.Group;

import junit.framework.TestCase;


public class TestCaseGroupDAO extends TestCase
{

	@Test
	public void testGetGroups()
	{
		GroupDAO groupDAO=new GroupDAO();
		ArrayList<Group> groupList=new ArrayList<Group>();
		
		groupList=groupDAO.getGroups();
		
		assertNotSame(null, groupList.size());
		
	}
	
	@Test
	public void testAddGroup()
	{
       GroupDAO groupDAO=new GroupDAO();
		
		
		//Positive Test
		
		String groupName="HR";
	    boolean testResult=groupDAO.addGroup(groupName);
	    assertTrue(testResult);
	    
	    //Negative Test
	    
	    String groupName1="PHPkjfhdsjfkdkfjdjkfadskfjdlkgjfklgjfdklgfsifoiejriejiodjsadcjdsieijei";
	    boolean testResult1=groupDAO.addGroup(groupName1);
	    assertFalse(testResult1);
		  
		
	}
	
	@Test
	public void testDelete()
	{
		GroupDAO groupDAO=new GroupDAO();
		
		/*
		//Positive Test
		
		String groupName="HR";
	    boolean testResult=groupDAO.delete(groupName);
	    assertTrue(testResult);
	    
	    */
	    //Negative Test
	    
	    String groupName1="PHP";
	    boolean testResult1=groupDAO.delete(groupName1);
	    assertFalse(testResult1);
		  
		
	}
	
	
}
