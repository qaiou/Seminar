package controller;

import dao.AssignmentDAO;
import model.Assignment;

public class AssignmentContr {
    private AssignmentDAO assignmentDAO = new AssignmentDAO();

    public boolean assign(int sessionId, int submissionId, String evaluatorId) {
        Assignment a = new Assignment();
        a.setSessionId(sessionId);
        a.setSubmissionId(submissionId);
        a.setEvaluatorId(evaluatorId);
        a.setStatus("Assigned");
        return assignmentDAO.insertAssignment(a);
    }
}
