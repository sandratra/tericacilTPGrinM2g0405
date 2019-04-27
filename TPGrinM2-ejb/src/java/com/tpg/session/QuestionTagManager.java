/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.QuestionTag;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
@LocalBean
public class QuestionTagManager {
    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;

    private void save(Object object) {
        em.persist(object);
    }
    
    private void update(Object object){
        em.merge(object);
    }
    
    public void createQuestionTag(QuestionTag questionTag){
        save(questionTag);
    }
    
    public void updateQuestionTag(QuestionTag questionTag){
        update(questionTag);
    }
    
    public QuestionTag getQuestionTag(int id) {  
        return em.find(QuestionTag.class, id);  
    }
}
