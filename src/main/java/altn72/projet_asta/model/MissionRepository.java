package altn72.projet_asta.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Integer> {
    List<Mission> findByApprentice_Id(Integer id);
}
