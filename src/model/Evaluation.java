package model;

public class Evaluation {
    private String studentId;
    private String evaluatorId;
    private int sessionId;

    private int problemClarity;
    private int assignmentId;
    private int methodology;
    private int results;
    private int presentation;
    private String comments;

    public Evaluation(String studentId, String evaluatorId, int sessionId,
                      int problemClarity, int methodology, int results,
                      int presentation, String comments) {
        this.studentId = studentId;
        this.evaluatorId = evaluatorId;
        this.sessionId = sessionId;
        this.problemClarity = problemClarity;
        this.methodology = methodology;
        this.results = results;
        this.presentation = presentation;
        this.comments = comments;
    }

    public int getTotalScore() {
        return problemClarity + methodology + results + presentation;
    }

    public String getStudentId() { return studentId; }
    public String getEvaluatorId() { return evaluatorId; }
    public int getSessionId() { return sessionId; }
    public int getProblemClarity() { return problemClarity; }
    public int getMethodology() { return methodology; }
    public int getResults() { return results; }
    public int getPresentation() { return presentation; }
    public String getComments() { return comments; }
    public int getAssignmentId() { return assignmentId; }
}
