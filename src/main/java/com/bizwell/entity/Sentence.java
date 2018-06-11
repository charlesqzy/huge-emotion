package com.bizwell.entity;

import java.util.List;

/**
 * Created by charles on 2017/11/21.
 */
public class Sentence {
    private Long id;
    private Long commentId;
    private String content;
    private String expression;
    private double score;
    private List<Word> concernedWordList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Word> getConcernedWordList() {
        return concernedWordList;
    }

    public void setConcernedWordList(List<Word> concernedWordList) {
        this.concernedWordList = concernedWordList;
    }
}
