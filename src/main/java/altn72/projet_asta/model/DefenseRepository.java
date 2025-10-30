package altn72.projet_asta.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DefenseRepository extends JpaRepository<Defense,Integer> {
    List<Defense> findByApprentice_Id(Integer apprenticeId);
    Boolean existsByDefenseDate(LocalDate defenseDate);
}
