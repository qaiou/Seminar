package controller;


import dao.AssignmentDAO;
import dao.EvaluatorDAO;
import dao.EvaluationDAO;
import model.Assignment;
import model.Evaluator;
import model.Evaluation;

import java.util.List;

public class EvaluatorContr {

    private EvaluatorDAO evaluatorDAO = new EvaluatorDAO();
    private AssignmentDAO assignmentDAO = new AssignmentDAO();
    private EvaluationDAO evaluationDAO = new EvaluationDAO();

    // Get all presentations assigned to this evaluator
    public List<Assignment> getAssignedPresentations(String evaluatorId) {
        return assignmentDAO.getByEvaluator(evaluatorId);
    }

    // Submit evaluation marks
    public boolean submitEvaluation(Evaluation e) {
        return evaluationDAO.insertEvaluation(e);
    }

    public List<Evaluator> getAllEvaluators() {
        return evaluatorDAO.getAll();
    }

}
