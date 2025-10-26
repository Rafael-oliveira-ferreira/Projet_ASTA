package altn72.projet_asta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @Column(name = "keywords", length = 500)
    private String keywords;

    @Column(name = "target_job", length = 100)
    private String targetJob;

    @Column(name = "comments", length = 2000)
    private String comments;

}