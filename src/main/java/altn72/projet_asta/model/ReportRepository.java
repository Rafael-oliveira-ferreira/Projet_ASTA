package altn72.projet_asta.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    List<Report> findByApprentice_Id(Integer id);
}
