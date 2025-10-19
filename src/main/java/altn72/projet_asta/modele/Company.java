package altn72.projet_asta.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private Integer id;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Lob
    @Column(name = "address", nullable = false)
    private String address;

    @Lob
    @Column(name = "access_info", nullable = false)
    private String accessInfo;

}