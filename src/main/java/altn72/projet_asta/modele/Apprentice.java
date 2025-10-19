package altn72.projet_asta.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "apprentice")
public class Apprentice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apprentice_id", nullable = false)
    private Integer id;

    @Column(name = "program", nullable = false, length = 50)
    private String program;

    @Column(name = "academic_year", nullable = false, length = 9)
    private String academicYear;

    @Column(name = "major", length = 100)
    private String major;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "phone", length = 10)
    private String phone;

}