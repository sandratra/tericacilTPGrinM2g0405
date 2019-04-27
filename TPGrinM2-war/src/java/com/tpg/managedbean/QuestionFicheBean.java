/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.managedbean;

import com.tpg.entity.Answer;
import com.tpg.entity.Question;
import com.tpg.session.AnswerManager;
import com.tpg.session.QuestionManager;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hobia
 */
@Named(value = "questionFicheBean")
@ViewScoped
public class QuestionFicheBean implements Serializable{

    private int id;
    private Question question;
    private List<Answer> listAnswer;

    @EJB
    QuestionManager questionManager;
    @EJB
    AnswerManager answerManager;


    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    /**
     * Creates a new instance of QuestionFicheBean
     */
    public void setListAnswer(List<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public void loadQuestion() {
        this.question = questionManager.getQuestion(id);
        this.listAnswer = answerManager.getAnswersQuestion(id);
    }

    public QuestionFicheBean() {
    }

}
