/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.servlet.ServletException;

/**
 *
 * @author asus
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @Inject
    private SecurityContext securityContext;
    @Inject
    private FacesContext facesContext;
    @Inject
    private ExternalContext externalContext;
   @Inject
    private HttpServletRequest httpServletRequest;

    private String nom;
    private String motDePasse;

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void login() {
        Credential credential
                = new UsernamePasswordCredential(nom, new Password(motDePasse));
        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                withParams().credential(credential));
        if (status.equals(SEND_CONTINUE)) {
            facesContext.responseComplete();
            System.out.println("OK");
        } else if (status.equals(SEND_FAILURE)) {
            addError(facesContext, "Nom / mot de passe incorrects");
        }
    }

    /**
     * Ajoute une erreur à afficher dans la page de login.
     *
     * @param facesContext
     * @param authentication_failed
     */
    private void addError(FacesContext facesContext,
            String message) {
        facesContext.addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        message,
                        null));
    }
    
    public String logout() {
    try {
      httpServletRequest.logout();
      httpServletRequest.getSession().invalidate();
    } catch (ServletException ex) {
      System.err.println("Erreur pendant logout : " + ex);
    }
    return null; // reste sur la page index.xhtml
  }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

}
