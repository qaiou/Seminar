package model;

public class Session {
    private int sessionId;
    private String sessionDate;
    private String venue;
    private String sessionType;
    private String status;

    public Session() {}

    public Session(String sessionDate, String venue, String sessionType) {
        this.sessionDate = sessionDate;
        this.venue = venue;
        this.sessionType = sessionType;
        this.status = "Scheduled";
    }

    public Session(int sessionId, String sessionDate, String venue,
                   String sessionType, String status) {
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.venue = venue;
        this.sessionType = sessionType;
        this.status = status;
    }

    public void setSessionId(String sessionDate){ this.sessionDate = sessionDate;}
    public void setSessionDate(int sessionId){ this.sessionId = sessionId;}
    public void setVenue(String venue){ this.venue = venue;}
    public void setStatus(String status){ this.status = status;}
    public void setSessionType(String sessionType){ this.sessionType = sessionType;}

    public int getSessionId(){ return sessionId;}
    public String getSessionDate(){ return sessionDate;}
    public String getVenue(){ return venue;}
    public String getStatus(){ return status;}
    public String getSessionType(){ return sessionType;}

    
    
}
