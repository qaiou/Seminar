package model;

public class Assignment {
    private int assignmentId;
    private String studentId;
    private String evaluatorId;
    private int submissionId;
    private int sessionId;
    private String status;

    public Assignment(){};
    public Assignment( String studentId, String evaluatorId, int sessionId) {
        this.studentId = studentId;
        this.evaluatorId = evaluatorId;
        this.sessionId = sessionId;
    }

    public Assignment( int sessionId, int submissionId, String evaluatorId, String status) {
        this.sessionId = sessionId;
        this.submissionId = submissionId;
        this.evaluatorId = evaluatorId;
        this.sessionId = sessionId;
    }

    //at evaluator panel, list of sessions of an evauator
    public Assignment( int submissionId, int sessionId, String studentId, String evaluatorId, String status) {
        this.submissionId = submissionId;
        this.studentId = studentId;
        this.evaluatorId = evaluatorId;
        this.sessionId = sessionId;
        this.status = status;
    }

    public void  setStudentId(String studentId) { this.studentId = studentId; }
    public void setEvaluatorId(String evaluatorId) { this.evaluatorId = evaluatorId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }
    public void setStatus(String status) { this.status = status; }
    public void setSubmissionId(int submissionId) { this.submissionId = submissionId; }

    public String getStudentId() { return studentId; }
    public String getEvaluatorId() { return evaluatorId; }
    public int getSessionId() { return sessionId; }
    public int getAssignmentId() { return assignmentId; }
    public String getStatus() { return status; }
    public int getSubmissionId() { return submissionId; }
}
