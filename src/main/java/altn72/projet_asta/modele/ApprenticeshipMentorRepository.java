package altn72.projet_asta.modele;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApprenticeshipMentorRepository extends JpaRepository<ApprenticeshipMentor,Integer> {
    Optional<ApprenticeshipMentor> findByIdAccountId(Integer idAccount);
}
