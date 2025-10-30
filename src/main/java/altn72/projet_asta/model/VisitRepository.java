package altn72.projet_asta.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit,Integer> {
    List<Visit> findByApprentice_Id(Integer id);
    Boolean existsByVisitDate(LocalDate visitDate);
}
