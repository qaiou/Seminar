package controller;

import dao.AssignmentDAO;
import model.Assignment;

public class AssignmentContr {
    private AssignmentDAO assignmentDAO = new AssignmentDAO();

    public boolean assign(int sessionId, String studentId, String evaluatorId) {
        Assignment a = new Assignment();
        a.setSessionId(sessionId);
        a.setStudentId(studentId);
        a.setEvaluatorId(evaluatorId);
        a.setStatus("Assigned");
        return assignmentDAO.insertAssignment(a);
    }
}
