package altn72.projet_asta.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprenticeRepository extends JpaRepository<Apprentice,Integer> {
    List<Apprentice> findByApprenticeshipMentor_Id(Integer mentorId);
    Boolean existsByEmail(String email);

    @Query("""
    SELECT DISTINCT a FROM Apprentice a
        LEFT JOIN Company c ON a.company.id = c.id
        LEFT JOIN Mission m ON a.id = m.apprentice.id
        WHERE a.apprenticeshipMentor.id = :mentorId
          AND (
                :query IS NULL
                OR :query = ''
                OR (
                    LOWER(a.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
                    OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
                    OR LOWER(a.major) LIKE LOWER(CONCAT('%', :query, '%'))
                    OR LOWER(c.companyName) LIKE LOWER(CONCAT('%', :query, '%'))
                   )
              )
""")
    List<Apprentice> searchApprentices(@Param("mentorId") Integer mentorId, @Param("query") String query);

}
