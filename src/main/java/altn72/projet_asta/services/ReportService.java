package altn72.projet_asta.services;

import altn72.projet_asta.modele.Report;
import altn72.projet_asta.modele.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report getReportById(int idMission) {
        return reportRepository.findById(idMission).orElse(null);
    }
}
