package model;

public class Assignment {
    private int assignmentId;
    private String studentId;
    private String evaluatorId;
    private int sessionId;
    private String status;

    public Assignment(){};
    public Assignment( String studentId, String evaluatorId, int sessionId) {
        this.studentId = studentId;
        this.evaluatorId = evaluatorId;
        this.sessionId = sessionId;
    }
    public Assignment( int assignmentId, int sessionId, String studentId, String evaluatorId, String status) {
        this.assignmentId = assignmentId;
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

    public String getStudentId() { return studentId; }
    public String getEvaluatorId() { return evaluatorId; }
    public int getSessionId() { return sessionId; }
    public int getAssignmentId() { return assignmentId; }
    public String getStatus() { return status; }
}
