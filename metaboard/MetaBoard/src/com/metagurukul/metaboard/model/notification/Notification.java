package com.metagurukul.metaboard.model.notification;

import java.sql.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;

public class Notification extends ActionForm
{
	int notificationID, creatorID, sectionID;
	String title, description;
	java.sql.Timestamp postedTime, expiryDate;
	int archived;
	int groupID;
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getArchived() 
	{
		return archived;
	}
	public void setArchived(int archived) 
	{
		this.archived = archived;
	}
	public int getNotificationID() 
	{
		return notificationID;
	}
	public void setNotificationID(int notificationID) 
	{
		this.notificationID = notificationID;
	}
	public int getCreatorID() 
	{
		return creatorID;
	}
	public void setCreatorID(int creatorID) 
	{
		this.creatorID = creatorID;
	}
	public int getSectionID() 
	{
		return sectionID;
	}
	public void setSectionID(int sectionID) 
	{
		this.sectionID = sectionID;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title.trim();
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description.trim();
	}
	public Timestamp getPostedTime() 
	{
		return postedTime;
	}
	public void setPostedTime(Timestamp postedTime) 
	{
		this.postedTime = postedTime;
	}
	public Timestamp getExpiryDate() 
	{
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) 
	{
		this.expiryDate = expiryDate;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	{
		
		
        ActionErrors errors = new ActionErrors();
        
        
        if (title == null || title.equals("")) 
        {
            errors.add("title", new ActionMessage("error.title.required"));
        }
        else
        {
        	if(!title.matches("[a-zA-Z0-9 .,&]+"))
        	{
                errors.add("title", new ActionMessage("error.title.wrong"));
        	}
        }
        if (description == null || description.equals(""))
        {
            errors.add("description", new ActionMessage("error.description.required"));

        }
        if (expiryDate == null)
        {
            errors.add("expiryDate", new ActionMessage("error.expiryDate.required"));

        }
        
        return errors;
    }
}
