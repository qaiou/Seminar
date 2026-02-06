package controller;

import dao.*;

public class ReportController {

    public String generateReport() {
        // pull from DAOs and format
        return "SEMINAR REPORT\n-----------------\n(loaded from DB)";
    }

    public String computeAwards() {
        return "AWARD RESULTS\n-----------------\n(computed from DB)";
    }
}
