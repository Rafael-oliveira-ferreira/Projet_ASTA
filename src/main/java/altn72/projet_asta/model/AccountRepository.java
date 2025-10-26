package altn72.projet_asta.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<UserAccount,Integer> {
    Optional<UserAccount> findByUsername(String username);
    boolean existsByUsername(String username);
}
