/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khalidmohamed.jsf.page;

import com.khalidmohamed.jsf.bean.SampleViewScopedBean;
import java.security.Principal;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Khalid
 */
@Named("welcomePageBean")
@RequestScoped
public class WelcomePageBean {
    private String welcomeUserName;
    private String completedGreeting;

    
    @Inject
    SampleViewScopedBean viewBean;
    
    @Inject
    HttpSession session;
    
    @Inject
    ServletContext servContext;
    
    @Inject
    Principal currentUser;
    /**
     * @return the welcomeUserName
     */
    public String getWelcomeUserName() {
        return welcomeUserName;
    }

    /**
     * @param welcomeUserName the welcomeUserName to set
     */
    public void setWelcomeUserName(String welcomeUserName) {
        this.welcomeUserName = welcomeUserName;
    }

    /**
     * @return the completedGreeting
     */
    public String getCompletedGreeting() {
        return completedGreeting;
    }

    /**
     * @param completedGreeting the completedGreeting to set
     */
    public void setCompletedGreeting(String completedGreeting) {
        this.completedGreeting = completedGreeting;
    }
    
    public void sayHello(){
        completedGreeting = "Hello, " + welcomeUserName;
        FacesMessage messageToQ = new FacesMessage(FacesMessage.SEVERITY_INFO,completedGreeting, "We're so happy to see you!");
        FacesContext.getCurrentInstance().addMessage("inputTextBox", messageToQ);
    }
    
    public String navigateToFlashPage(){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("transmittedVariable",viewBean.getDogs().get(0));
        return "flashscope.xhtml?faces-redirect=true";
    }
    
    public void ajaxTriggered(){
        Logger.getAnonymousLogger().info("Ajax triggered log-statement");
    }

    public void alsoAjaxTriggered(AjaxBehaviorEvent abe){
        Logger.getAnonymousLogger().info(abe.getComponent().getId());
    }
    
    public void isRefreshed(){
        FacesContext.getCurrentInstance().isPostback();
        FacesContext.getCurrentInstance().validationFailed();
    }
}
