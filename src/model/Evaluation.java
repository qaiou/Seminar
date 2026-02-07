package model;

public class Evaluation {
    private int evaluationID;
    private int assignmentID;
    private int problemClarity;
    private int methodology;
    private int results;
    private int presentation;
    private String comments;
    private int totalScore;

    public Evaluation(int assignmentID, int problemClarity, int methodology, int results, int presentation, String comments) {
        this.assignmentID = assignmentID;
        this.problemClarity = problemClarity;
        this.methodology = methodology;
        this.results = results;
        this.presentation = presentation;
        this.comments = comments;
        this.totalScore = problemClarity + methodology + results + presentation;
    }

    public Evaluation(int evaluationID, int assignmentID, int problemClarity, int methodology, int results, int presentation, String comments, int totalScore) {
        this.evaluationID = evaluationID;
        this.assignmentID = assignmentID;
        this.problemClarity = problemClarity;
        this.methodology = methodology;
        this.results = results;
        this.presentation = presentation;
        this.comments = comments;
        this.totalScore = totalScore;
    }

    public int getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(int evaluationID) {
        this.evaluationID = evaluationID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getProblemClarity() {
        return problemClarity;
    }

    public void setProblemClarity(int problemClarity) {
        this.problemClarity = problemClarity;
    }

    public int getMethodology() {
        return methodology;
    }

    public void setMethodology(int methodology) {
        this.methodology = methodology;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}