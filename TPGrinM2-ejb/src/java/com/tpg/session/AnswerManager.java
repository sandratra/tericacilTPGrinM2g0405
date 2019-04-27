/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.Answer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hobia
 */
@Stateless
@LocalBean
public class AnswerManager {
 @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;

    private void save(Object object) {
        em.persist(object);
    }
    
    private void update(Object object){
        em.merge(object);
    }
    
    public void createAnswer(Answer answer){
        save(answer);
    }
    
    public void updateAnswer(Answer Answer){
        update(Answer);
    }
    
    public List<Answer> getAnswersQuestion(int idquestion){
        String requete = "select a from Answer a where ";
        List<Answer> liste = null;
        //liste = query.getResultList(); 
        return liste;
    }
}
