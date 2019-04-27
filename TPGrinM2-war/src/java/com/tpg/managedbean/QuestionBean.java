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
    
    @EJB
    QuestionManager questionManager;
    
    @EJB
    QuestionTagManager qtm;
    
    @EJB
    TagManager tagManager;
    
    private Question question;
    private Tag tag;

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
    
    
    
    public void saveQuestion() {
        question = new Question();
        question.setContent(content);
        question.setTitle(title);
        //question.setDatepost(datePost);
        question.setResolved(false);
        questionManager.createQuestion(question);
        
        tag = new Tag();
        tag.setLabel(label);
        tagManager.insertQuestionTag(label, question);
    }
    /**
     * Creates a new instance of QuestionBean
     */
    public QuestionBean() {
    }
    
}
