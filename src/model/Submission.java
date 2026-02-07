package model;

public class Submission {
    private int submissionID;
    private String studentID;
    private String title;
    private String abstractText;
    private String supervisor;
    private String type;
    private String filePath;
    private String status;

    public Submission(int submissionID, String studentID, String title, String abstractText, String supervisor, String type, String filePath, String status) {
        this.submissionID = submissionID;
        this.studentID = studentID;
        this.title = title;
        this.abstractText = abstractText;
        this.supervisor = supervisor;
        this.type = type;
        this.filePath = filePath;
        this.status = status;
    }

    public int getSubmissionID() {
        return submissionID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getType() {
        return type;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Used for the Sequence Diagram "getDetails()" arrow
    public String getDetails() {
        return "Title: " + title + "\nAbstract: " + abstractText + "\nSupervisor: " + supervisor;
    }
}