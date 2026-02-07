package model;

public class Award {
    private int awardId;
    private int submissionId;
    private String awardType;
    private int score;

    public Award(int submissionId, String awardType, int score) {
        this.submissionId = submissionId;
        this.awardType = awardType;
        this.score = score;
    }

    public Award(int awardId, int submissionId, String awardType, int score) {
        this.awardId = awardId;
        this.submissionId = submissionId;
        this.awardType = awardType;
        this.score = score;
    }

    public int getAwardId() { return awardId; }
    public int getSubmissionId() { return submissionId; }
    public String getAwardType() { return awardType; }
    public int getScore() { return score; }
}
