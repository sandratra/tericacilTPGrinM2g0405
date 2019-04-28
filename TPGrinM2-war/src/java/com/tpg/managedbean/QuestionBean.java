/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.managedbean;

import com.tpg.entity.Question;
import com.tpg.entity.Tag;
import com.tpg.session.QuestionManager;
import com.tpg.session.QuestionTagManager;
import com.tpg.session.TagManager;
import com.tpg.util.Util;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author asus
 */
@Named(value = "questionBean")
@RequestScoped
public class QuestionBean {
    private String title;
    private String content;
    private String datePost;
    private String label;
    
    private String recherche;
    private List<Question> listQuestion;
    
    @EJB
    QuestionManager questionManager;
    
    @EJB
    QuestionTagManager qtm;
    
    @EJB
    TagManager tagManager;
    
    private Question question;
    private Tag tag;

    public List<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Question getQuestion() {
        return question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRecherche() {
        return recherche;
    }

    public void setRecherche(String recherche) {
        this.recherche = recherche;
    }
    
    
    
    public String saveQuestion() {
        question = new Question();
        question.setContent(content);
        question.setTitle(title);
        //question.setDatepost(datePost);
        question.setResolved(false);
        questionManager.createQuestion(question);
        
        tag = new Tag();
        tag.setLabel(label);
        tagManager.insertQuestionTag(label, question);
        
        return "question-fiche.xhtml?id="+question.getId()+"&amp;faces-redirect=true;includeViewParams=true";
    }
    /**
     * Creates a new instance of QuestionBean
     */
    
    public void loadListQuestion(){
        String search = Util.recherche(recherche);
        this.listQuestion = questionManager.getListQuestion(search);
    }
    
    public QuestionBean() {
    }
    
}
