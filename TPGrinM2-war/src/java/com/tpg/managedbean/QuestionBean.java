/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.managedbean;

import com.tpg.entity.Question;
import com.tpg.session.QuestionManager;
import java.sql.Date;
import javax.annotation.ManagedBean;
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
    
    @EJB
    QuestionManager questionManager;
    
    private Question question;

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
    
    public void saveQuestion() {
        question = new Question();
        question.setContent(content);
        question.setTitle(title);
        //question.setDatepost(datePost);
        question.setResolved(false);
        questionManager.createQuestion(question);
    }
    /**
     * Creates a new instance of QuestionBean
     */
    public QuestionBean() {
    }
    
}
