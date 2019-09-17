package com.auth.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

import com.auth.forms.AuthForm;
import com.metagurukul.metaboard.dao.MemberDAO;
import com.metagurukul.metaboard.model.member.Member;

public class SocialAuthSuccessAction extends Action {

        final Log LOG = LogFactory.getLog(SocialAuthSuccessAction.class);

        @Override
        public ActionForward execute(final ActionMapping mapping,
                        final ActionForm form, final HttpServletRequest request,
                        final HttpServletResponse response) throws Exception {

                AuthForm authForm = (AuthForm) form;
                Map<String,String> userInfo=new HashMap<String, String>();
                SocialAuthManager manager = null;
                if (authForm.getSocialAuthManager() != null) {
                        manager = authForm.getSocialAuthManager();
                }
                if (manager != null) {
                        List<Contact> contactsList = new ArrayList<Contact>();
                        Profile profile = null;
                        try {
                                Map<String, String> paramsMap = new HashMap<String, String>();
                                for (Object obj :request.getParameterMap().entrySet() ) {
                                    Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) obj;
                                    String key = entry.getKey();
                                    
                                    String values[] = entry.getValue();
                                    if(key.equals("openid.ext1.value.lastname"))
                                    {
                                    	userInfo.put("lastName", values[0]);
                                    }
                                    if(key.equals("openid.ext1.value.firstname"))
                                    {
                                    	userInfo.put("firstName", values[0]);
                                    }
                                    if(key.equals("openid.ext1.value.email"))
                                    {
                                    	userInfo.put("emailID", values[0]);
                                    }
                                    
                                    
                                    paramsMap.put(key, values[0].toString()); // Only 1 value is
                                }
                                /*AuthProvider provider=null;
                                try{
                                //provider = manager.connect(paramsMap);
                                	provider = manager.connect(SocialAuthUtil.getRequestParametersMap(request));
                                }
                                catch(Exception e){
                                	
                                	System.out.println("Error in connect...........................");
                                }

                                profile = provider.getUserProfile();
                                contactsList = provider.getContactList();
                                if (contactsList != null && contactsList.size() > 0) {
                                        for (Contact p : contactsList) {
                                                if (StringUtils.isEmpty(p.getFirstName())
                                                                && StringUtils.isEmpty(p.getLastName())) {
                                                        p.setFirstName(p.getDisplayName());
                                                }
                                        }
                                }
                                */
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }        
                        //System.out.println("Emailid is..........."+profile.getFirstName());
                        //request.setAttribute("profile", profile);
                        //request.setAttribute("contacts", contactsList);
                        
                        
                        HttpSession session=request.getSession();
                        session.setAttribute("userInfo",userInfo);
                       
                        MemberDAO mDAO=new MemberDAO();
                        
                        Member memberInfo=mDAO.getMemberInfo(userInfo.get("emailID"));
                        session.setAttribute("emailID", userInfo.get("emailID"));
                        if(memberInfo!=null)
                        {
                        	session.setAttribute("userType", memberInfo.getCatID());
                        	session.setAttribute("userName", memberInfo.getName());
                        	session.setAttribute("emailID", userInfo.get("emailID"));
                            session.setAttribute("groupID", memberInfo.getGroupID());
                            session.setAttribute("memberID", memberInfo.getMemberID());
                        }
                        else
                        {
                        	return mapping.findForward("register");
                        }
                       
                        return mapping.findForward("success");
                       
                // if provider null
                //return mapping.findForward("failure");
          }
}

