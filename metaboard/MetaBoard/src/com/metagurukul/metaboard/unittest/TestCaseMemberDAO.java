package com.metagurukul.metaboard.unittest;

import junit.framework.*;
import org.junit.Test;
import com.metagurukul.metaboard.dao.MemberDAO;
import com.metagurukul.metaboard.model.member.Member;
import com.metagurukul.metaboard.dao.*;
public class TestCaseMemberDAO extends TestCase
{
	@Test
   public void testRegister()
   {
	   MemberDAO memberDAO=new MemberDAO();
	   
	   Member member=new Member();
	   //Positive Testing
	   String name="Ashish";
	   String emailID="ashish.sharma@metagurukul.com";
	   String contact="9602181180";
	   
	   member.setName(name);
	   member.setEmailID(emailID);
	   member.setContact(contact);
	   member.setGroupID(1);
	   member.setCatID(1);
	   
	   boolean testResult = memberDAO.register(member);
	  
	   assertTrue(testResult);
	   
	   
	   //Negative Testing
	   
	   String name1="Ashish";
	   String emailID1="ashish.sharmametagurukul.comsddsklsjfkldsjfklsdjfkdscnkjfjedijewoijedksjdlksajdsalkdjseakld";
	   String contact1="9602181180";
	   
	   member.setName(name1);
	   member.setEmailID(emailID1);
	   member.setContact(contact1);
	   member.setGroupID(1);
	   member.setCatID(1);
	   
	   boolean testResult1 = memberDAO.register(member);
	 
	   assertFalse(testResult1);
   }
   
   @Test
   public void testIsMember()
   {
	   //Positive Testing
	   MemberDAO memberDAO=new MemberDAO();
	   
	   Member member=new Member();
	   
	   String emailId="ashish.sharma@metagurukul.com";
	   
	   boolean testResult=memberDAO.isMember(emailId);
	   
	   assertTrue(testResult);
	   
	   
	   //Negative Testing
	   
	   String fakeEmailId="sharma.ashish985@gmail.com";
	   
	   boolean testResult1=memberDAO.isMember(fakeEmailId);
	   assertFalse(testResult1);
   }
   
   
   @Test
   public void testGetMemberInfo()
   {
	  
       MemberDAO memberDAO=new MemberDAO();
	   
       Member member=new Member();
	   
	   String emailId="jinesh.goyal@metagurukul.com";
	   
	   //expectedInfo
       String  memberId,name,contact;
       int groupId,catId;
       
       //Positive Testing
	   member=memberDAO.getMemberInfo(emailId);
	   
	   assertEquals(10,member.getMemberID());
	   assertEquals("Jinesh",member.getName());
	   assertEquals("9785791283",member.getContact());
	   assertEquals(5,member.getGroupID());
	   assertEquals(1,member.getCatID());
	   
	   //Negative Testing
	   
	   String fakeEmailId="sharma.ashish985";
	   
	   member=memberDAO.getMemberInfo(fakeEmailId);
	   //here expected value of member object is null
	   assertEquals(null,member);
	   
   }
   
}
