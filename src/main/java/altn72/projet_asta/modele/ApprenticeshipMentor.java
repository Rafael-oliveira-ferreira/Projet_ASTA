package altn72.projet_asta.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "apprenticeship_mentor")
public class ApprenticeshipMentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id", nullable = false)
    private Integer id;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "position_title", length = 100)
    private String positionTitle;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "phone", length = 10)
    private String phone;

}