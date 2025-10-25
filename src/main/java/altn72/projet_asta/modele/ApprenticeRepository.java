package altn72.projet_asta.modele;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprenticeRepository extends JpaRepository<Apprentice,Integer> {
    List<Apprentice> findByApprenticeshipMentor_Id(Integer mentorId);
}
