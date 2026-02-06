package controller;

import dao.SessionDAO;
import model.Session;

public class AssignmentContr {
    private SessionDAO sessionDAO;

    public AssignmentContr(SessionDAO dao) {
        this.sessionDAO = dao;
    }

    public void assignPresenter(int sessionId, String presenterId) {
        Session session = sessionDAO.getSessionById(sessionId);
        if (session != null) {
            session.addPresenter(presenterId);
            sessionDAO.updateSession(session);
        }
    }

    public void assignEvaluator(int sessionId, String evaluatorId) {
        Session session = sessionDAO.getSessionById(sessionId);
        if (session != null) {
            session.addEvaluator(evaluatorId);
            sessionDAO.updateSession(session);
        }
    }
}
