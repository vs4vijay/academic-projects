package com.metagurukul.metaboard.unittest;

import java.util.ArrayList;

import org.junit.Test;

import com.metagurukul.metaboard.dao.GroupDAO;
import com.metagurukul.metaboard.dao.SectionDAO;
import com.metagurukul.metaboard.model.section.Section;

import junit.framework.TestCase;


public class TestCaseSectionDAO extends TestCase
{
	
   
	@Test
   public void testGetSection()
   {
	   SectionDAO sectionDAO=new SectionDAO();
	   ArrayList<Section> sectionList=new ArrayList<Section>();
	   
	   sectionList=sectionDAO.getSections();
	   
	   assertNotSame(null, sectionList.size());
	   
   }
	
	@Test
	public void testAddSection()
	{
       SectionDAO sectionDAO=new SectionDAO();
		
		
		//Positive Test
		
		String sectionName="B'day";
	    boolean testResult=sectionDAO.addSection(sectionName);
	    assertTrue(testResult);
	    
	    //Negative Test
	    
	    String sectionName1="PHPkjfhdsjfkdkfjdjkfadskfjdsifoiejriejiodjsadcjdsieijei";
	    boolean testResult1=sectionDAO.addSection(sectionName1);
	    assertTrue(testResult1);
		  
		
	}
	
	@Test
	public void testDelete()
	{
		SectionDAO sectionDAO=new SectionDAO();
		
		
		//Positive Test
		
		String sectionName="B'day";
	    boolean testResult=sectionDAO.delete(sectionName);
	    assertTrue(testResult);
	    
	   
	    //Negative Test
	    
	    String sectionName1="PHP";
	    boolean testResult1=sectionDAO.delete(sectionName1);
	    assertFalse(testResult1);
		  
		
	}
	
}
