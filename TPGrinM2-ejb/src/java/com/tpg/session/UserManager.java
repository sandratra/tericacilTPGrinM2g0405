/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.Member;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author EBI
 */

@Stateless
@LocalBean
public class UserManager {

    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;

    private void save(Object object) {
        em.persist(object);
    }
    
    private void update(Object object){
        em.merge(object);
    }
    
    public void createUser(Member user){
        save(user);
    }
    
    public void updateUser(Member user){
        update(user);
    }
    public Member findByName(String name) {
       Query query = em.createNamedQuery("Member.findByName");
       query.setParameter("name", name);
       List<Member> list = query.getResultList(); 
       if(list.size()>0) return list.get(0);
       return null;
    }
}
