package com.example.aashray;

public class Question {


    private String question;
    private String question_key;
    private int response;
    //private static final int NO_IMAGE_PROVIDED = -1;
    private int imageResourceId;

    public Question(String question_key, String question){//, int imageResourceId) {
        this.question = question;
        this.question_key = question_key;
        //this.imageResourceId = imageResourceId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_key() {
        return question_key;
    }

    public void setQuestion_key(String question_key) {
        this.question_key = question_key;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
