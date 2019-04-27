/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.UserType;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EBI
 */
@Stateless
@LocalBean
public class UserTypeManager {

    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;
    
    private void update(Object object){
        em.merge(object);
    }
    
    public long createUserType(UserType user){
        em.persist(user);
        //em.flush();
        return user.getId();               
    }
    
    public void updateUserType(UserType user){
        update(user);
    }
}
