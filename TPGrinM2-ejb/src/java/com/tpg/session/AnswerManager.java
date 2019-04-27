/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.session;

import com.tpg.entity.Answer;
import com.tpg.entity.Member;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hobia
 */
@Stateless
@LocalBean
public class AnswerManager {

    @PersistenceContext(unitName = "TPGrinM2-ejbPU")
    private EntityManager em;
    
    @EJB
    UserManager userManager;


    @Inject
    FacesContext facesContext;

    private void save(Object object) {
        em.persist(object);
    }

    private void update(Object object) {
        em.merge(object);
    }

    public void createAnswer(Answer answer) {
        String name = facesContext.getExternalContext().getUserPrincipal().getName();
        Member user = userManager.findByName(name);
        answer.setUser(user);
        save(answer);
    }

    public void updateAnswer(Answer Answer) {
        update(Answer);
    }

    public List<Answer> getAnswersQuestion(int idquestion) {
        String requete = "select a from Answer a where a.question.id = :idquestion order by a.date asc";
        Query query = em.createQuery(requete);
        query.setParameter("idquestion", idquestion);

        List<Answer> liste = null;
        liste = query.getResultList();
        return liste;
    }
}
