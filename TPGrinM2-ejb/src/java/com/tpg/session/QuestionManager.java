/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.Member;
import com.tpg.entity.Question;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
@LocalBean
public class QuestionManager {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;
    
    @EJB
    UserManager userManager;
    
    @Inject
    FacesContext facesContext;

    private void save(Object object) {
        em.persist(object);
    }
    
    private void update(Object object){
        em.merge(object);
    }
    
    public void createQuestion(Question question){
        String name = facesContext.getExternalContext().getUserPrincipal().getName();
        Member user = userManager.findByName(name);
        question.setUser(user);
        save(question);
    }
    
    public void updateQuestion(Question question){
        update(question);
    }
    
    public Question getQuestion(long id) {  
        return em.find(Question.class, id);  
    }
    
}
