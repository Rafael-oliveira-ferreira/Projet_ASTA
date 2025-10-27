package altn72.projet_asta.controller;

import altn72.projet_asta.model.Defense;
import altn72.projet_asta.model.Report;
import altn72.projet_asta.model.dto.MissionDto;
import altn72.projet_asta.model.dto.ReportDto;
import altn72.projet_asta.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/{idReport}")
    public Report getReportById(@PathVariable Integer idReport) {
        return reportService.getReportById(idReport);
    }

    @PostMapping("/createReport/")
    public ResponseEntity<Report> createCompany(@RequestBody Report newReport) {
        Report report = reportService.addReport(newReport);
        return ResponseEntity.ok(report);
    }

    @PutMapping("/updateReport/{idReport}")
    public void updateReport(@PathVariable Integer idReport, @RequestBody Report report) {
        reportService.updateReport(idReport, report);
    }

    @GetMapping("/reportByApprenticeId/{idApprentice}")
    public List<ReportDto> getReportsByMentorId(@PathVariable("idApprentice") Integer idApprentice) {
        return reportService.findByApprenticeId(idApprentice).stream()
                .map(r -> new ReportDto(
                        r.getId(), r.getApprentice() != null ? r.getApprentice().getId() : null,
                        r.getSubject(), r.getGrade(), r.getComments()
                ))
                .toList();
    }
}
