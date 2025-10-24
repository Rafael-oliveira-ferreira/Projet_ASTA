package altn72.projet_asta.controller;

import altn72.projet_asta.modele.Report;
import altn72.projet_asta.services.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/{idReport}")
    public Report getReportById(@PathVariable Integer idReport) {
        return reportService.getReportById(idReport);
    }
}
