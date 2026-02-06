package model;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private int sessionId;
    private String date;
    private String venue;
    private String type; // Oral / Poster
    private List<String> presenters;
    private List<String> evaluators;

    public Session(int sessionId, String date, String venue, String type) {
        this.sessionId = sessionId;
        this.date = date;
        this.venue = venue;
        this.type = type;
        this.presenters = new ArrayList<>();
        this.evaluators = new ArrayList<>();
    }

    // Getters and Setters
    public int getSessionId() { return sessionId; }
    public String getDate() { return date; }
    public String getVenue() { return venue; }
    public String getType() { return type; }

    public List<String> getPresenters() { return presenters; }
    public List<String> getEvaluators() { return evaluators; }

    // Business logic
    public void addPresenter(String presenterId) {
        presenters.add(presenterId);
    }

    public void addEvaluator(String evaluatorId) {
        evaluators.add(evaluatorId);
    }
}