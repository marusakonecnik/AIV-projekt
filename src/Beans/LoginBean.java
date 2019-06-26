package Beans;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value="login")
@Stateless
public class LoginBean {

    @Resource
    private SessionContext context;
    private String username;

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentUser(){
        return context.getCallerPrincipal().getName();
    }




}
