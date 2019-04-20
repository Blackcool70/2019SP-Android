package com.example.wtstudentsay1005064.geoquiz;

public class Question {
    private String statement;
    private boolean answer;

    public void setStatement(String statement){
        this.statement = statement;
    }
    public void setAnswer(boolean answer){
        this.answer = answer;
    }
    public String getStatement(){
        return statement;
    }

    public boolean getAnswer(){
        return answer;
    }
}
