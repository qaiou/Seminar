package model;

public class Session {
    private int sessionId;
    private String sessionDate;
    private String venue;
    private String sessionType;
    private String status;
    private String time;

    public Session() {}

    public Session(String sessionDate, String venue, String sessionType) {
        this.sessionDate = sessionDate;
        this.venue = venue;
        this.sessionType = sessionType;
        this.status = "Scheduled";
        //this.time = time;
    }

    public Session(int sessionId, String sessionDate, String venue,
                   String sessionType, String status) {
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.venue = venue;
        this.sessionType = sessionType;
        this.status = status;
    }

    public void setSessionDate(String sessionDate){ this.sessionDate = sessionDate;}
    public void setSessionId(int sessionId){ this.sessionId = sessionId;}
    public void setVenue(String venue){ this.venue = venue;}
    public void setStatus(String status){ this.status = status;}
    public void setSessionType(String sessionType){ this.sessionType = sessionType;}
    public void setTime(String time){ this.time = time;}

    public int getSessionId(){ return sessionId;}
    public String getSessionDate(){ return sessionDate;}
    public String getVenue(){ return venue;}
    public String getStatus(){ return status;}
    public String getSessionType(){ return sessionType;}
    public String getTime(){ return time;}

    
    
}
