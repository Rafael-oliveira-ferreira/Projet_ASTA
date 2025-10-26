package altn72.projet_asta.model;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_account", nullable = false)
    private UserAccount idAccount;

    public Integer getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getPositionTitle() {
        return this.positionTitle;
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

    public UserAccount getIdAccount() {
        return this.idAccount;
    }
}