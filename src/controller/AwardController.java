package controller;

import dao.*;
import model.*;
import java.util.*;

public class AwardController {

    private EvaluationDAO evaluationDAO = new EvaluationDAO();
    private AwardDAO awardDAO = new AwardDAO();

    public String computeAwards() {
        List<EvaluationResult> results = evaluationDAO.getAverageScoresPerSubmission();

        if (results.isEmpty()) return "No evaluations found.";

        EvaluationResult bestOverall = Collections.max(results, Comparator.comparingInt(EvaluationResult::getAverageScore));
        EvaluationResult bestOral = results.stream()
                .filter(r -> r.getPresentationType().equals("Oral"))
                .max(Comparator.comparingInt(EvaluationResult::getAverageScore))
                .orElse(null);
        EvaluationResult bestPoster = results.stream()
                .filter(r -> r.getPresentationType().equals("Poster"))
                .max(Comparator.comparingInt(EvaluationResult::getAverageScore))
                .orElse(null);

        StringBuilder sb = new StringBuilder("AWARD RESULTS\n----------------------\n");

        if (bestOverall != null) {
            awardDAO.insert(new Award(bestOverall.getSubmissionId(), "Best Overall", bestOverall.getAverageScore()));
            sb.append(" Best Overall: ").append(bestOverall.getTitle()).append("\n");
        }
        if (bestOral != null) {
            awardDAO.insert(new Award(bestOral.getSubmissionId(), "Best Oral", bestOral.getAverageScore()));
            sb.append(" Best Oral: ").append(bestOral.getTitle()).append("\n");
        }
        if (bestPoster != null) {
            awardDAO.insert(new Award(bestPoster.getSubmissionId(), "Best Poster", bestPoster.getAverageScore()));
            sb.append(" Best Poster: ").append(bestPoster.getTitle()).append("\n");
        }

        return sb.toString();
    }
}
