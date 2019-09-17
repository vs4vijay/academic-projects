package com.metagurukul.metaboard.dao;

import java.util.*;
import java.sql.*;

import com.metagurukul.metaboard.model.notification.*;

public class NotificationDAO 
{
	Connection con = ConnectionFactory.getConnection();
	
	public boolean post(String[] groups, Notification notification)
	{
		int rows = -1;
		String notiQuery = "INSERT INTO notifications(title, description, creator_id, section_id, expiry_date, archived) VALUES (?, ?, ?, ?, ?, ?) ";
		//int notificationID = notification.getNotificationID();
		String groupNotiQuery = "INSERT INTO group_notification VALUES (?, ?) ";
		String title = notification.getTitle();
		String description = notification.getDescription();
		int creatorID = notification.getCreatorID();
		int sectionID = notification.getSectionID();
		java.sql.Timestamp expiryDate = notification.getExpiryDate();
		int archived = notification.getArchived();
		
		PreparedStatement psNoti = null;
		PreparedStatement psGroupNoti = null; 
		try
		{
			psNoti = con.prepareStatement(notiQuery);
			psNoti.setString(1, title);
			psNoti.setString(2, description);
			psNoti.setInt(3, creatorID);
			psNoti.setInt(4, sectionID);
			psNoti.setTimestamp(5, expiryDate);
			psNoti.setInt(6, archived);
			rows = psNoti.executeUpdate();
			psNoti.close();
			int nID=getLastNotificationID();
			psGroupNoti=con.prepareStatement(groupNotiQuery);
			for(String group:groups)
			{
				psGroupNoti.setInt(1,Integer.parseInt(group));
				psGroupNoti.setInt(2,nID);
				rows=psGroupNoti.executeUpdate();
			}
			psGroupNoti.close();
			
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
				if (psGroupNoti != null) 
				{
					psGroupNoti.close();
				}
				if (psNoti != null) 
				{
					psNoti.close();
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
	 
	private int getLastNotificationID() {
		int nID=-1;
		String query = "SELECT max(n_id) FROM notifications";
		Statement ps = null;
		ResultSet rs = null;
		try 
		{
			ps = con.createStatement();
			rs = ps.executeQuery(query);

			if(rs.next())
			{
				nID=rs.getInt(1);
			}
			else
				return nID;
		}
		catch (SQLException e) 
		{
				e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		 
		return nID;
	}
	
	
	public boolean remove()
	{
		int rows = -1;
		String query = "UPDATE notifications SET archived = ? WHERE expiry_date < now()";
		PreparedStatement ps = null;
		try
		{
			 ps = con.prepareStatement(query);
			 ps.setInt(1, 1);
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
	
	
	 
	public boolean delete(int notificationID)
	{
		int rows = -1;
		String query = "DELETE FROM notifications where n_id = ?";
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement(query);
			ps.setInt(1, notificationID);
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
	 
	public boolean update(int notificationID, Notification notification)
	{
		int rows=-1;
		String query = "UPDATE notifications SET title = ?, description = ?, creator_id = ?, posted_time = ?, section_id = ?, expiry_date = ?, archived = ? WHERE n_id = ?";
		String title = notification.getTitle();
		String description = notification.getDescription();
		int creator_id = notification.getCreatorID();
		java.sql.Timestamp posted_time = notification.getPostedTime();
		int section_id = notification.getSectionID();
		java.sql.Timestamp expiry_dt = notification.getExpiryDate();
		int archived = notification.getArchived();
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setInt(3, creator_id);
			ps.setTimestamp(4, posted_time);
			ps.setInt(5, section_id);
			ps.setTimestamp(6, expiry_dt);
			ps.setInt(7, archived);
			ps.setInt(8, notificationID);
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
	 
	public ArrayList searchByTitle(String groupid,String title,int sectionID)
	{
		List<Notification> notificationList = new ArrayList<Notification>();
		int groupID=Integer.parseInt(groupid);
		String query = "SELECT * FROM notifications WHERE section_id = ? AND title LIKE ? " +
				" AND n_id IN(SELECT n_id FROM group_notification WHERE group_id=? OR group_id=(SELECT group_id FROM groups WHERE group_name LIKE 'public'))" +
				" ORDER BY posted_time desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			ps = con.prepareStatement(query);
			ps.setInt(1,sectionID);
			ps.setString(2,"%" + title + "%");
			ps.setInt(3,groupID);
			rs = ps.executeQuery();

			System.out.println("rsnexttt");
			
			if(rs.next())
			{
				System.out.println("rsnext222");
				
				rs.beforeFirst();
				while (rs.next()) 
				{
					Notification notification = new Notification();
					notification.setNotificationID(rs.getInt(1));
					notification.setTitle(rs.getString(2)); 
					notification.setDescription(rs.getString(3));
					notification.setCreatorID(rs.getInt(4));
					notification.setPostedTime(rs.getTimestamp(5));
					notification.setSectionID(rs.getInt(6));	
					notification.setExpiryDate(rs.getTimestamp(7));
					notification.setArchived(rs.getInt(8));
					notificationList.add(notification);
					
					System.out.println("rsnext333");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return (ArrayList) notificationList;
	 }
	
	
	public ArrayList searchByDate(String groupid, String searchVal,int sectionID)
	{
		List<Notification> notificationList = new ArrayList<Notification>();
		int groupID=Integer.parseInt(groupid);
		String query = "SELECT * FROM notifications WHERE date(posted_time) = '"+searchVal+"' AND section_id=? AND n_id IN  ( SELECT n_id FROM group_notification WHERE group_id=? OR group_id=(SELECT group_id FROM groups WHERE group_name LIKE 'public')) ORDER BY posted_time desc";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			ps = con.prepareStatement(query);
			
			ps.setInt(1,sectionID);
			ps.setInt(2,groupID);
			rs = ps.executeQuery();

			if(rs.next())
			{
				rs.beforeFirst();
				while (rs.next()) 
				{
					Notification notification = new Notification();
					notification.setNotificationID(rs.getInt(1));
					notification.setTitle(rs.getString(2)); 
					notification.setDescription(rs.getString(3));
					notification.setCreatorID(rs.getInt(4));
					notification.setPostedTime(rs.getTimestamp(5));
					notification.setSectionID(rs.getInt(6));	
					notification.setExpiryDate(rs.getTimestamp(7));
					notification.setArchived(rs.getInt(8));
					notificationList.add(notification);
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
				if (ps != null) 
				{
					ps.close();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
		}
		return (ArrayList) notificationList;
	 }
	 
	public ArrayList searchByCreator(String groupid,String creatorName,int sectionID)
	{
		List<Notification> notificationList=new ArrayList<Notification>();
		int groupID=Integer.parseInt(groupid);
		
		String query = "SELECT * FROM notifications WHERE creator_id IN ( select m_id FROM members WHERE name LIKE ? ) AND section_id=? AND n_id IN ( SELECT n_id FROM group_notification WHERE group_id=? OR group_id=(SELECT group_id FROM groups WHERE group_name LIKE 'public'))" +
				" ORDER BY posted_time desc";
		System.out.println();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setString(1,"%" + creatorName + "%");
		 	ps.setInt(2,sectionID);
		 	ps.setInt(3,groupID);
			rs = ps.executeQuery();

			if(rs.next())
			{
				rs.beforeFirst();
				while (rs.next()) 
				{
					Notification notification = new Notification();
					notification.setNotificationID(rs.getInt(1));
					notification.setTitle(rs.getString(2)); 
					notification.setDescription(rs.getString(3));
					notification.setCreatorID(rs.getInt(4));
					notification.setPostedTime(rs.getTimestamp(5));
					notification.setSectionID(rs.getInt(6));	
					notification.setExpiryDate(rs.getTimestamp(7));
					notification.setArchived(rs.getInt(8));
					notificationList.add(notification);
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
		return (ArrayList) notificationList;
	}
	
	public ArrayList<Notification> show(String groupid, int sectionID)
	{
		ArrayList<Notification> notificationList=new ArrayList<Notification>();
		int groupID=Integer.parseInt(groupid);
		String query = "SELECT * FROM notifications WHERE archived=0 AND section_id = ? AND n_id IN (SELECT n_id from group_notification WHERE group_id=? or group_id=(SELECT group_id FROM groups WHERE group_name LIKE 'public'))" +
				" ORDER BY posted_time desc ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setInt(1,sectionID);
		 	ps.setInt(2,groupID);
			rs = ps.executeQuery();

			if(rs.next())
			{
				
				rs.beforeFirst();
				while (rs.next()) 
				{
					Notification notification = new Notification();
					notification.setNotificationID(rs.getInt(1));
					notification.setTitle(rs.getString(2)); 
					notification.setDescription(rs.getString(3));
					notification.setCreatorID(rs.getInt(4));
					notification.setPostedTime(rs.getTimestamp(5));
					notification.setSectionID(rs.getInt(6));	
					notification.setExpiryDate(rs.getTimestamp(7));
					notification.setArchived(rs.getInt(8));
					notificationList.add(notification);
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
		return notificationList;
	}

	public ArrayList<Notification> show(int sectionID)
	{
		ArrayList<Notification> notificationList=new ArrayList<Notification>();
		String query = "SELECT * FROM notifications WHERE archived=0 AND section_id = ? AND n_id IN (SELECT n_id from group_notification WHERE group_id=(SELECT group_id FROM groups WHERE group_name LIKE 'public'))" +
				" ORDER BY posted_time desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setInt(1,sectionID);
			rs = ps.executeQuery();

			if(rs.next())
			{
				
				rs.beforeFirst();
				while (rs.next()) 
				{
					Notification notification = new Notification();
					notification.setNotificationID(rs.getInt(1));
					notification.setTitle(rs.getString(2)); 
					notification.setDescription(rs.getString(3));
					notification.setCreatorID(rs.getInt(4));
					notification.setPostedTime(rs.getTimestamp(5));
					notification.setSectionID(rs.getInt(6));	
					notification.setExpiryDate(rs.getTimestamp(7));
					notification.setArchived(rs.getInt(8));
					notificationList.add(notification);
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
		return notificationList;
	}
	
	public ArrayList<Notification> showMyPost(String memberId,int sectionID)
	{
		ArrayList<Notification> notificationList=new ArrayList<Notification>();
		int memberID=Integer.parseInt(memberId);
		String query = "SELECT * FROM notifications WHERE section_id = ? AND creator_id=?" +
				" ORDER BY posted_time desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setInt(1,sectionID);
		 	ps.setInt(2,memberID);
			rs = ps.executeQuery();

			if(rs.next())
			{
				
				rs.beforeFirst();
				while (rs.next()) 
				{
					Notification notification = new Notification();
					notification.setNotificationID(rs.getInt(1));
					notification.setTitle(rs.getString(2)); 
					notification.setDescription(rs.getString(3));
					notification.setCreatorID(rs.getInt(4));
					notification.setPostedTime(rs.getTimestamp(5));
					notification.setSectionID(rs.getInt(6));	
					notification.setExpiryDate(rs.getTimestamp(7));
					notification.setArchived(rs.getInt(8));
					notificationList.add(notification);
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
		return notificationList;
	}
	
	
	
	public String getCreatorName(int creatorID)
	{
		System.out.println("creator ID= "+creatorID);
		String creatorName=null;
		String query="SELECT name from members where m_id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setInt(1,creatorID);
			rs = ps.executeQuery();
		    if(rs.next())
			creatorName=rs.getString(1);
		    else
		    creatorName="anonymous";	
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
				if (ps != null) 
				{
					ps.close();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
		}
		return creatorName;
	}
	public String getCreatorEmailID(int creatorID)
	{
		//System.out.println("creator ID= "+creatorID);
		String creatorEmailID=null;
		String query="SELECT email_id from members where m_id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setInt(1,creatorID);
			rs = ps.executeQuery();
		    if(rs.next())
			creatorEmailID=rs.getString(1);
		    else
		    creatorEmailID="anonymous";	
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
				if (ps != null) 
				{
					ps.close();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
		}
		return creatorEmailID;
	}
	public ArrayList<String> getgroupName(int notificationID)
	{
		ArrayList<String> groupList=new ArrayList<String>();
		String query="SELECT group_name from groups where group_id IN(select group_id from group_notification where n_id=?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
		 	ps = con.prepareStatement(query);
		 	ps.setInt(1,notificationID);
			rs = ps.executeQuery();
			while(rs.next())
			groupList.add(rs.getString(1));	
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
				if (ps != null) 
				{
					ps.close();
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

	public boolean updateArchive(int archived,int notificationID) {
		int rows = -1;
		String query = "UPDATE notifications SET archived = ? WHERE n_id = ?";
		PreparedStatement ps = null;
		try
		{
			 ps = con.prepareStatement(query);
			 ps.setInt(1, archived);
			 ps.setInt(2, notificationID);
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
	
	
}