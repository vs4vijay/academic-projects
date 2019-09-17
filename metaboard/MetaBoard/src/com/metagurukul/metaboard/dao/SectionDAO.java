package com.metagurukul.metaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.metagurukul.metaboard.model.notification.Notification;
import com.metagurukul.metaboard.model.section.Section;

public class SectionDAO {

	Connection con = ConnectionFactory.getConnection();
	
	public ArrayList<Section> getSections()
	{
		ArrayList<Section> sectionList=new ArrayList<Section>();
		
		String query = "SELECT * FROM section_type";
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
					Section section=new Section();
					section.setSectionID(rs.getInt(1));
					section.setSectionName(rs.getString(2));
					sectionList.add(section);
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
		return sectionList;
	}
	public boolean delete(String sectionName)
	{
		int rows = -1;
		String query = "DELETE FROM section_type where section_name = ?";
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement(query);
			ps.setString(1, sectionName);
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

	public boolean addSection(String sectionName)
	{
		int rows = -1;
		String query = "INSERT INTO section_type(section_name) VALUES (?)";
		
		PreparedStatement psSection = null;
		 
		try
		{
			psSection = con.prepareStatement(query);
			psSection.setString(1, sectionName);
			
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
	public boolean exist(String sectionName)
	{
		
		String query = "SELECT * FROM section_type where section_name=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try 
		{
		 	stmt = con.prepareStatement(query);
		 	stmt.setString(1,sectionName);
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
