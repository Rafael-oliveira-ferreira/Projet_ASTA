package altn72.projet_asta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "apprenticeship_mentor_id")
    @JsonIgnore
    private ApprenticeshipMentor apprenticeshipMentor;

    public Integer getId() {
        return this.id;
    }

    public String getProgram() {
        return this.program;
    }

    public String getAcademicYear() {
        return this.academicYear;
    }

    public String getMajor() {
        return this.major;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Company getCompany() {
        return this.company;
    }

    public ApprenticeshipMentor getApprenticeshipMentor() {
        return this.apprenticeshipMentor;
    }
}