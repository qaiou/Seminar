package dao;

import java.util.HashMap;
import java.util.Map;
import model.Session;

public class SessionDAO {
    private Map<Integer, Session> sessionDB = new HashMap<>();

    public void saveSession(Session session) {
        sessionDB.put(session.getSessionId(), session);
    }

    public Session getSessionById(int id) {
        return sessionDB.get(id);
    }

    public void updateSession(Session session) {
        sessionDB.put(session.getSessionId(), session);
    }
}
