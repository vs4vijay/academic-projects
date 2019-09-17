package com.metagurukul.metaboard.dao;

import java.sql.*;

public class ConnectionFactory 
{
	
	public static final String connectionString = "jdbc:mysql://localhost:3306/metaboard";
	public static final String password = "root";
	//public static final String connectionString = "jdbc:mysql://mysql-metaboard.jelastic.servint.net/test";
	//public static final String password = "wENfgaJ329";
	public static final String userId = "root";
	public static Connection con = null;
	
	public static Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Error in Loading Class");
			e.printStackTrace();
		}
		
		try 
		{
			con = DriverManager.getConnection(connectionString, userId, password);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error in Getting Connection");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static boolean closeConnection()
	{
		if(con != null)
		{
			try 
			{
				con.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error in Closing Connection");
				e.printStackTrace();
				return false;
			}			
		}
		return true;
	}
}

