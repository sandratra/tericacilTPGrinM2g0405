/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.managedbean;

import com.tpg.entity.Member;
import com.tpg.session.UserManager;
import com.tpg.utils.HashMdp;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author asus
 */
@Named(value = "inscriptionBean")
@RequestScoped
public class InscriptionBean {

    @EJB
    UserManager userManager;

    @Inject
    // Pour coder le mot de passe
    private HashMdp passwordHash;

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private Member member;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of InscriptionBean
     */
    public InscriptionBean() {
    }

    public void inscrire() {
        member = new Member();
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setEmail(email);
        String mdp = passwordHash.generate(password);
        member.setPassword(mdp);

        userManager.createUser(member);

    }

}
