/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.Question;
import com.tpg.entity.QuestionTag;
import com.tpg.entity.Tag;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
@LocalBean
public class TagManager {
    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;
    
    @EJB
    private QuestionTagManager qtm;

    private void save(Object object) {
        em.persist(object);
    }
    
    private void update(Object object){
        em.merge(object);
    }
    
    public void createTag(Tag tag){
        save(tag);
    }
    
    public void updateTag(Tag tag){
        update(tag);
    }
    
    public Tag getTag(int id) {  
        return em.find(Tag.class, id);  
    }
    
    public Tag tagExiste(String label){
        String requete = "select a from Tag a where upper(a.label) = upper(:label)";
        Query query = em.createQuery(requete);
        query.setParameter("label", label);
        
        List<Tag> liste = null;
        liste = query.getResultList(); 
        if(liste.size() > 0) return liste.get(0);
        return null;
    }
    
    public void insertQuestionTag(String tagLabel, Question question){
        String[] label = tagLabel.split("/");
        for(int i=0; i< label.length;i++){
            Tag temp = this.tagExiste(label[i]);
            QuestionTag temp1 = new QuestionTag();
            if(temp==null){
                temp = new Tag();
                temp.setLabel(label[i]);
                this.createTag(temp);
                temp1.setTag(temp);
                temp1.setQuestion(question);
                qtm.createQuestionTag(temp1);
            }else {
                temp1.setTag(temp);
                temp1.setQuestion(question);
                qtm.createQuestionTag(temp1);
            }
        }
    }
    
    public List<Tag> getTags(int idquestion){
        String requete = "select a.tag from QuestionTag a where a.question.id = :idquestion";
        Query query = em.createQuery(requete);
        query.setParameter("idquestion", idquestion);
        List<Tag> liste = null;
        liste = query.getResultList(); 
        return liste;
    }
    
    public void deleteTag(int idquestion){
        String requete = "delete from QuestionTag a where a.question.id = :idquestion";
        Query query = em.createQuery(requete);
        query.setParameter("idquestion", idquestion);
        query.executeUpdate();
    }
}
