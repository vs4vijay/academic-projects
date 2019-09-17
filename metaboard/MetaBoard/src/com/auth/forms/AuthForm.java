package com.auth.forms;



import org.apache.struts.action.ActionForm;
import org.brickred.socialauth.SocialAuthManager;
public class AuthForm extends ActionForm {      
        String id;      
        SocialAuthManager socialAuthManager;
        public String getId() {
                return id;
        }       
        public void setId(final String id) {
                this.id = id;
        }
        public SocialAuthManager getSocialAuthManager() {
                return socialAuthManager;
        }

        public void setSocialAuthManager(final SocialAuthManager socialAuthManager) {
                this.socialAuthManager = socialAuthManager;
        }
}
