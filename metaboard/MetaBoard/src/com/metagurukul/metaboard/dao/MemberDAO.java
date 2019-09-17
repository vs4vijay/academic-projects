package com.metagurukul.metaboard.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import com.metagurukul.metaboard.model.member.*;

public class MemberDAO 
{
	Connection con = ConnectionFactory.getConnection();

	public boolean register(Member member)
	{
		
		String query = "INSERT INTO members(name, email_id, contact, group_id, cat_id,dob) VALUES (?, ?, ?, ?, ?,?)";
		PreparedStatement prStatement;
		int affactedRows;
		try 
		{
			prStatement = con.prepareStatement(query);
			prStatement.setString(1, member.getName());
			prStatement.setString(2, member.getEmailID());
			prStatement.setString(3, member.getContact());
			prStatement.setInt(4, member.getGroupID());
			prStatement.setInt(5, member.getCatID());
			prStatement.setDate(6, member.getDob());
			affactedRows = prStatement.executeUpdate();
			System.out.println("member inserted");
		} 
		catch (SQLException e) 
		{
			System.out.println("[###] Error in Preparing Statement");
			e.printStackTrace();
			return false;
		}
		
		
		
		if (affactedRows > 0)
			return true;
		else
			return false;
	}
	
	public boolean isMember(String email)
	{
		String query = "SELECT * FROM metaboard WHERE email_id = " + email;
		Statement statement;
		ResultSet resultSet;
		try 
		{
			statement = con.createStatement();
		} 
		catch (SQLException e) 
		{
			System.out.println("[###] Error in Creating Statement");
			e.printStackTrace();
			return false;
		}
		
		try 
		{
			resultSet = statement.executeQuery(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("[###] Error in Executing Query");
			e.printStackTrace();
			return false;
		}
		
		try 
		{
			return resultSet.next();
		} 
		catch (SQLException e) 
		{
			System.out.println("[###] Error in Result Set");
			e.printStackTrace();
			return false;
		}
		
	}
	public Member getMemberInfo(String emailID)
	{
		String query="SELECT * FROM members WHERE email_id = ?";
		
		ResultSet resultSet=null;
		Member member=null;
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, emailID);
			resultSet=ps.executeQuery();
			
		}
		catch(Exception e)
		{
			System.out.println("[###]Error in creating statement");
			e.printStackTrace();
			return null;
		}
		
		
		
		try 
		{
			if(resultSet.next())
			{
				member=new Member();
				member.setMemberID(resultSet.getInt(1));
				member.setName(resultSet.getString(2));
				member.setEmailID(resultSet.getString(3));
				member.setContact(resultSet.getString(4));
				member.setGroupID(resultSet.getInt(5));
				member.setCatID(resultSet.getInt(6));
			}
		}
		catch (SQLException e) 
		{
			System.out.println("[###] Error in Result Set");
			e.printStackTrace();
			return null;
			
		}
	return member;
		
	}
	
	public ArrayList<Member> getMembersByCurrentBirthDay()
	{
		String query="SELECT * FROM members WHERE DAY(dob) = DAY(CURRENT_DATE) AND MONTH(dob) =  MONTH(CURRENT_DATE);";
		ArrayList<Member> memberList = new ArrayList<Member>();
		ResultSet resultSet=null;
		Member member=null;
		try
		{
			Statement ps=con.createStatement();
			resultSet=ps.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println("[###]Error in creating statement bday");
			e.printStackTrace();
			return null;
		}
		
		try 
		{
			while(resultSet.next())
			{
				member=new Member();
				member.setMemberID(resultSet.getInt(1));
				member.setName(resultSet.getString(2));
				member.setEmailID(resultSet.getString(3));
				member.setContact(resultSet.getString(4));
				member.setGroupID(resultSet.getInt(5));
				member.setCatID(resultSet.getInt(6));
				member.setDob(resultSet.getDate(7));
				memberList.add(member);
			}
		}
		catch (SQLException e) 
		{
			System.out.println("[###] Error in Result Set");
			e.printStackTrace();
			return null;
			
		}
		
		
		return memberList;
		
	}
	
	
	protected void finalize()
	{
		// Closing Connection by Garbage Collector
		ConnectionFactory.closeConnection();
	}
	
}
