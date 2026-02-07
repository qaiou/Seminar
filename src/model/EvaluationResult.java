package model;

public class EvaluationResult {
    private int submissionId;
    private String title;
    private String presentationType;
    private int averageScore;

    public EvaluationResult(int submissionId, String title,
                            String presentationType, int averageScore) {
        this.submissionId = submissionId;
        this.title = title;
        this.presentationType = presentationType;
        this.averageScore = averageScore;
    }

    public int getSubmissionId() { return submissionId; }
    public String getTitle() { return title; }
    public String getPresentationType() { return presentationType; }
    public int getAverageScore() { return averageScore; }
}
