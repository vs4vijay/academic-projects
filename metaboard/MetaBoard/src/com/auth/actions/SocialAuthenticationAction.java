package com.auth.actions;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

import com.auth.forms.AuthForm;


public class SocialAuthenticationAction extends Action {

        final Log LOG = LogFactory.getLog(SocialAuthenticationAction.class);

        @Override
        public ActionForward execute(final ActionMapping mapping,
                        final ActionForm form, final HttpServletRequest request,
                        final HttpServletResponse response) throws Exception {

                AuthForm authForm = (AuthForm) form;
                  
                String id = authForm.getId();
                 
                SocialAuthManager manager;
                if (authForm.getSocialAuthManager() != null) {
                        manager = authForm.getSocialAuthManager();
                        System.out.println(" maanager");
                } else {
                        InputStream in = SocialAuthenticationAction.class.getClassLoader()
                                        .getResourceAsStream("oauth_consumer.properties");
                        SocialAuthConfig conf = SocialAuthConfig.getDefault();
                        conf.load(in);
                        manager = new SocialAuthManager();
                        manager.setSocialAuthConfig(conf);
                        authForm.setSocialAuthManager(manager);
                        System.out.println("not maanager");
                }

                String returnToUrl = RequestUtils.absoluteURL(request,
                                "/socialAuthSuccessAction.do")
                                .toString();
                String url = manager.getAuthenticationUrl(id, returnToUrl);

                LOG.info("Redirecting to: " + url);
                if (url != null) {
                        ActionForward fwd = new ActionForward("openAuthUrl", url, true);
                        return fwd;
                }
                return mapping.findForward("home");
        }
}