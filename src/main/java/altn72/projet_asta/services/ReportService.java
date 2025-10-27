package altn72.projet_asta.services;

import altn72.projet_asta.model.Mission;
import altn72.projet_asta.model.Report;
import altn72.projet_asta.model.ReportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report getReportById(int idMission) {
        return reportRepository.findById(idMission).orElse(null);
    }

    public Report addReport(Report report) {
        return reportRepository.save(report);
    }

    public void updateReport(Integer idReport, Report report) {
        Report existingReport = reportRepository.findById(idReport).orElseThrow();
        if (existingReport != null) {
            BeanUtils.copyProperties(report, existingReport, "id");
            reportRepository.save(existingReport);
        }
    }

    public void deleteReport(int idMission) {
        reportRepository.deleteById(idMission);
    }

    public List<Report> findByApprenticeId(Integer apprenticeId) {
        return reportRepository.findByApprentice_Id(apprenticeId);
    }
}
