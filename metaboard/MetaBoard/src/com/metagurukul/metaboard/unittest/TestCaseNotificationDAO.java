package com.metagurukul.metaboard.unittest;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Test;
import junit.framework.TestCase;
import com.metagurukul.metaboard.dao.NotificationDAO;
import com.metagurukul.metaboard.model.notification.Notification;

public class TestCaseNotificationDAO extends TestCase
{
	@Test
	public void testPost()
	{
		NotificationDAO notificationDAO=new NotificationDAO();
		Notification notifications = new Notification();
		
		//Positive Test
		String title="Positive Test";
		String description="Positive Testing of Post!!";
      
		notifications.setCreatorID(2);
		notifications.setSectionID(1);
		notifications.setTitle(title);
		notifications.setDescription(description);
		notifications.setArchived(0);
        String[] group=new String[1];
        group[0]="4";
		boolean postResult=notificationDAO.post(group, notifications);
     
		assertTrue(postResult);
		
		//Negative Test
		String title1="Negative Test!!fgokjfdogkrpogtrekgfd bhgbvyfvtgyvgjyftyftjyftyttyutyutuytuiuiyuiyuiyuiyuiogkufiheruifhufihdiufhiufhduivhuhrufhuifhiudsfhiuirurhiufhdsuifhreieufdfihdiu";
		String description1="Negative Testing of Post!!";
      
		notifications.setCreatorID(2);
		notifications.setSectionID(1);
		notifications.setTitle(title1);
		notifications.setDescription(description1);
		notifications.setArchived(0);
		String[] group1=new String[1];
		group1[0]="8";
		boolean postResult1=notificationDAO.post(group1,notifications);
		
        assertFalse(postResult1);
		
	  }
	
	@Test
	public void testRemove()
	{
		NotificationDAO notificationDAO=new NotificationDAO();
		
		//Positive Test
		boolean removeResult=notificationDAO.remove();
		assertTrue(removeResult);
		
		
	}
	
	
	
    
	
	 @Test
	   public void testDelete()
	   {
		   NotificationDAO notificationDAO=new NotificationDAO();
		    
		   //Positive Test
		   boolean deleteResult=notificationDAO.delete(4);
		   assertTrue(deleteResult);
		   
		   //Negative Test
		   
		   boolean deleteResult1=notificationDAO.delete(50);
		   assertFalse(deleteResult1);
		   
	   }
	
	 
       @Test
	   public void testUpdate()
	    {
		   NotificationDAO notificationDAO=new NotificationDAO();
		   Notification notifications = new Notification();
	   
		   //Positive Test
		   String title="Advance Java - Final Quiz";
		   String description="Will be taken after Workshop!!";
	      
		   notifications.setCreatorID(2);
		   notifications.setSectionID(1);
		   notifications.setTitle(title);
		   notifications.setDescription(description);
		   notifications.setArchived(0);
	  
		   boolean updateResult=notificationDAO.update(2, notifications);
		   assertTrue(updateResult); 
	  }
       
	   @Test
	 	public void testSearchByTitle()
	 	{
		  NotificationDAO notificationDAO=new NotificationDAO();
		  
		  //Positive Test
		  String title="JDBC Dicussion";
		  String groupId="1";
		  ArrayList<Notification> notificationList = notificationDAO.searchByTitle(groupId,title, 1);
		  
		  assertNotSame(null, notificationList);
		  
		  //Negative Test
		  
		  String title1="Fake Title";
		  String groupId1="2";
		  ArrayList<Notification> notificationList1 = notificationDAO.searchByTitle(groupId1,title1, 1);
		  assertNull(notificationList1);
	   }
	   
	   
	   @Test
	   public void testSearchByDate()
	   {
	 	  NotificationDAO notificationDAO=new NotificationDAO();
	 	  
	 	  //Positive Test
	 	  java.sql.Timestamp dt=new Timestamp(2012, 11, 11, 01, 01, 01, 00);
	        
	 	  String groupId="1";
	 	  ArrayList<Notification> notificationList =notificationDAO.searchByDate(groupId, "2012-10-11", 1);
	 	  assertNotNull(notificationList);
	 	  
	 	  //Negative Test
	 	  
	 	  
	        
	 	  String groupId1="1";
	 	  ArrayList<Notification> notificationList1 =notificationDAO.searchByDate(groupId1, "2012-12-11", 1);
	 	  int size=notificationList1.size();
	 	  assertEquals(0, size);
	 	  
	   }
	   public void testSearchByCreator()
	   {
	 	  NotificationDAO notificationDAO=new NotificationDAO();
	 	  //Positive Test
	 	  String creatorName="Ashish";
	 	  String groupId="1";
	 	  ArrayList<Notification> notificationList =notificationDAO.searchByCreator(groupId,creatorName, 1);
	 	  
	 	  assertNotNull(notificationList);
	 	  
	 	  //Negative Test
	 	  
	 	 String creatorName1="Fake Name";
	 	 String groupId1="1";
	 	 ArrayList<Notification> notificationList1 =notificationDAO.searchByCreator(groupId1,creatorName1, 1);
	 	  
	 	 assertNotNull(notificationList1);
	 	 
	   }
	   
	   @Test
	   public void testShow1()
	   {
	 	  NotificationDAO notificationDAO=new NotificationDAO();
	 	  //Positive Test
	 	  int sectionId=1;
	 	  
	 	  ArrayList<Notification> notificationList=notificationDAO.show(sectionId);
	 	 
	 	  assertNotNull(notificationList);
	 	  
	 	  //Negative Test
	 	  
	 	  int sectionId1=3;
	 	  
	 	  ArrayList<Notification> notificationList1=notificationDAO.show(sectionId1);
	 	  
	 	  assertNull(notificationList1);
	 	}
	   
	   @Test
	   public void testShow2()
	   {
		   NotificationDAO notificationDAO=new NotificationDAO();
		  //Positive Test
		   String groupId="1";
		   int sectionId=1;
		   ArrayList<Notification> notificationList=notificationDAO.show(groupId,sectionId);
		   
		   assertNotSame(0,notificationList);
		   
		   //Negative Test
		   String groupId1="7";
		   int sectionId1=8;
		   ArrayList<Notification> notificationList1=notificationDAO.show(groupId1,sectionId1);
		   
		   assertSame(null, notificationList1);
	   }
	   
	   @Test
	   public void testGetCreatorName()
	   {
		   NotificationDAO notificationDAO=new NotificationDAO();
		   
		   //Positive Test
		   int creatorId=1;
		   String creatorName=notificationDAO.getCreatorName(creatorId);
		   
		   assertEquals("Ashish", creatorName);
		   
		   //Negative Test
		   int creatorId1=50;
		   String creatorName1=notificationDAO.getCreatorName(creatorId1);
		   assertEquals("anonymous",creatorName1);
	   }
	   
	   @Test
	   public void testGetGroupName()
	   {
		   NotificationDAO notificationDAO=new NotificationDAO();
		   
		   //Positive Test
		   int notificationId=1;
		   ArrayList<String> groupName =notificationDAO.getgroupName(notificationId);
		   
		   assertNotNull(groupName);
		   
		   //Negative Test
		   int notificationId1=50;
		   ArrayList<String> groupName1 =notificationDAO.getgroupName(notificationId1);
		   int size=groupName1.size();
		   assertEquals(0, size);
		   
	   }
	   
	  @Test
	  public void testShowMyPost()
	  {
		  NotificationDAO notificationDAO=new NotificationDAO();
		  
		  //Positive Test
		  String memberId="1";
		  int sectionID=1;
		  ArrayList<Notification> notificationList=new ArrayList<Notification>();
		  notificationList=notificationDAO.showMyPost(memberId, sectionID);
		  assertNotSame(null, notificationList);
		  
		  //Negative Test
		  String memberId1="100";
		  int sectionID1=40;
		  ArrayList<Notification> notificationList1=new ArrayList<Notification>();
		  notificationList1=notificationDAO.showMyPost(memberId1, sectionID1);
		 assertSame(null, notificationList1);
		  
	  }
	  
	  @Test
	  public void testGetCreatorEmailId()
	  {
		  NotificationDAO notificationDAO=new NotificationDAO();
		  //Positive Test
		  
		  int creatorId=1;
		  String emailId=notificationDAO.getCreatorEmailID(creatorId);
		  assertEquals("ashish.sharma@metagurukul.com", emailId);
		  
		  //Negative Test
		  int creatorId1=100;
		  String emailId1=notificationDAO.getCreatorEmailID(creatorId1);
		  assertEquals("anonymous", emailId1);
		  
		  
	  }
	  
	  @Test
	  public void testUpdateArchive()
	  {
		  NotificationDAO notificationDAO=new NotificationDAO();
		  //Positive Test
		  int archived=1;
		  int notificationID=1;
		  boolean result=notificationDAO.updateArchive(archived, notificationID);
		  assertTrue(result);
		  
		  //Negative Test
		  int archived1=1;
		  int notificationID1=150;
		  boolean result1=notificationDAO.updateArchive(archived1, notificationID1);
		  assertFalse(result1);
		  
		  
		  
		  
	  }
	   
}
