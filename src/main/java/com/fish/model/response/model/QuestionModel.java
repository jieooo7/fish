package com.fish.model.response.model;

import java.util.List;

/**
 * Created by thy on 16-11-25.
 */

public class QuestionModel {

    private String question;
    private String the_answer;
    private List<String> answers;

    public QuestionModel() {
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getThe_answer() {
        return the_answer;
    }

    public void setThe_answer(String the_answer) {
        this.the_answer = the_answer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
