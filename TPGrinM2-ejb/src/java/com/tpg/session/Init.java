/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.User;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

/**
 *
 * @author EBI
 */
@Singleton
@LocalBean
@Startup
public class Init {
    
    @EJB
    private UserManager userManager;
    
    /**
     *
     * @param user
     */
    @PostConstruct
    public void postInit(){
        User u = new User();
        u.setFirstname("FirstName");
        u.setLastname("LastName");
        userManager.createUser(u);
    }
}
