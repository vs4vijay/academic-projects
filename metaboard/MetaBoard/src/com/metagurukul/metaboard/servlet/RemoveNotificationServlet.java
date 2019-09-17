package com.metagurukul.metaboard.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth.actions.SocialAuthenticationAction;
import com.metagurukul.metaboard.dao.MemberDAO;
import com.metagurukul.metaboard.dao.NotificationDAO;
import com.metagurukul.metaboard.mail.MailUtil;
import com.metagurukul.metaboard.model.member.Member;
import com.metagurukul.metaboard.model.notification.Notification;

/**
 * Servlet implementation class RemoveNotificationServlet
 */
public class RemoveNotificationServlet extends HttpServlet 
{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		 Timer timer = new Timer();
			
			timer.schedule( new TimerTask() 
			{
				int i = 0;
				NotificationDAO notificationDAO = new NotificationDAO();
				@SuppressWarnings("deprecation")
				public void run() 
				{
				//	 notificationDAO.remove();
					// Create remove() method in DAO and call here..
					System.out.println("");
					System.out.println("[#] " + i++ + " Scheduled Task on " + ": " + new Date());
				//	MailUtil mail=new MailUtil();
				//	mail.sendMail("to", "title", "description");
				NotificationDAO notiDAO=new NotificationDAO();
				MemberDAO memberDAO=new MemberDAO();
				ArrayList<Member> members=memberDAO.getMembersByCurrentBirthDay();
				String groups[]=new String[1];
				
				InputStream in = SocialAuthenticationAction.class.getClassLoader()
                .getResourceAsStream("notification.properties");
				Properties notPro=new Properties();
				try {
					notPro.load(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				groups[0]=notPro.getProperty("groupID");
				for(Member member:members)
				{
					Notification notification=new Notification();
					notification.setTitle("Happy Birth Day");
					notification.setDescription("Best Wishes To "+member.getName());
					notification.setCreatorID(Integer.parseInt(notPro.getProperty("creatorID")));
					notification.setArchived(0);
					notification.setSectionID(Integer.parseInt(notPro.getProperty("sectionID")));
					notification.setExpiryDate(new java.sql.Timestamp(new java.util.Date().getTime()+(1000*60*60*24*Integer.parseInt(notPro.getProperty("expDay")))));
					notiDAO.post(groups, notification);
				}
				}
			} , 1000, 1000*60*60*24);
			
	}



	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveNotificationServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
       
		

		
	}



	
}
