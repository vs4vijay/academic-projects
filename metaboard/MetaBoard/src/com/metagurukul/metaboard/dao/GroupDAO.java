package com.metagurukul.metaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.metagurukul.metaboard.model.group.Group;
import com.metagurukul.metaboard.model.notification.Notification;

public class GroupDAO {
	Connection con = ConnectionFactory.getConnection();
	
	public ArrayList<Group> getGroups()
	{
		ArrayList<Group> groupList=new ArrayList<Group>();
		
		String query = "SELECT * FROM groups";
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
		 	stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next())
			{
			
				rs.beforeFirst();
				while (rs.next()) 
				{
					Group group=new Group();
					group.setGroupID(rs.getInt(1));
					group.setGroupName(rs.getString(2));
					groupList.add(group);
				}
			}
			else
				return null;
		 }
		catch (SQLException e) 
		{
				e.printStackTrace();
				return null;
		} 
		finally 
		{
			/* close connection */
			try 
			{
				//ConnectionFactory.closeConnection();
				if (rs != null) 
				{
					rs.close();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
		}
		return groupList;

			
		
	}
	public boolean delete(String groupName)
	{
		int rows = -1;
		String query = "DELETE FROM groups where group_name = ?";
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement(query);
			ps.setString(1, groupName);
			rows = ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		} 
		finally 
		{
			/* close connection */
			try 
			{
				//ConnectionFactory.closeConnection();
				if (ps != null) 
				{
					ps.close();
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(rows != 0)
			return true;
		else 
			return false;		 
	 }
	
	public boolean addGroup(String groupName)
	{
		int rows = -1;
		String query = "INSERT INTO groups(group_name) VALUES (?)";
		
		PreparedStatement psSection = null;
		 
		try
		{
			psSection = con.prepareStatement(query);
			psSection.setString(1, groupName);
			
			rows = psSection.executeUpdate();
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		} 
		finally 
		{
				/* close connection */
			try 
			{
				//ConnectionFactory.closeConnection();
				
				if (psSection != null) 
				{
					psSection.close();
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(rows != 0 )
			return true;
		else 
			return false;	 
	}
	public boolean exist(String groupName)
	{
		
		String query = "SELECT * FROM groups where group_name=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		 	stmt = con.prepareStatement(query);
		 	stmt.setString(1,groupName);
			rs = stmt.executeQuery();

			if(rs.next())
				return true;
			else
				return false;
		 }
		catch (SQLException e) 
		{
				e.printStackTrace();
				return true;
		} 
		finally 
		{
			/* close connection */
			try 
			{
				//ConnectionFactory.closeConnection();
				if (rs != null) 
				{
					rs.close();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		
	}

}
