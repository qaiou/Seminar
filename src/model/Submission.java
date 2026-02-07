package model;

public class Submission {
    private int submissionId;
    private String studentId;
    private String title;
    private String abstractText;
    private String supervisorName;
    private String presentationType;
    private String filePath;
    private String status;

    
    public Submission( String studentId, String title, String abstractText, String supervisorName, String presentationType, String filePath) {
        this.studentId = studentId;
        this.title = title;
        this.abstractText = abstractText;
        this.supervisorName = supervisorName;
        this.presentationType = presentationType;
        this.filePath = filePath;
    }

    public Submission( int submissionId, String studentId, String title, String abstractText, String supervisorName, String presentationType, String filePath, String status) {
        this.submissionId = submissionId;
        this.studentId = studentId;
        this.title = title;
        this.abstractText = abstractText;
        this.supervisorName = supervisorName;
        this.presentationType = presentationType;
        this.filePath = filePath;
        this.status = status;
    }

    public void  setStudentId(String studentId) { this.studentId = studentId; }
    public void setSubmissionId(int submissionId) { this.submissionId = submissionId; }
    public void setTitleId(String title) { this.title = title; }
    public void setAbstractTextId(String abstractText) { this.abstractText = abstractText; }
    public void setSupervisorName(String supervisorName) { this.supervisorName = supervisorName; }
    public void setPresentationType(String presentationType) { this.presentationType = presentationType; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public void setStatus(String status) { this.status = status; }

    public String getStudentId() { return studentId; }
    public int getSubmissionId() { return submissionId; }
    public String getTitle() { return title; }
    public String getAbstractText() { return abstractText; }
    public String getSupervisorName() { return supervisorName; }
    public String getPresentationType() { return presentationType; }
    public String getFilePath() { return filePath; }
    public String getStatus() { return status; }
    
}
