package controller;

import dao.SessionDAO;
import model.Session;
import java.util.List;

public class SessionController {
    private SessionDAO dao = new SessionDAO();

    public void createSession(String date, String venue, String type) {
        dao.insert(new Session(date, venue, type));
    }

    public List<Session> getAllSessions() {
        return dao.getAll();
    }
}
