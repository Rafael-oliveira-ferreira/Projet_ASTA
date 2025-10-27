package altn72.projet_asta.model;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @Column(name = "subject", length = 150)
    private String subject;

    @Column(name = "grade", precision = 5, scale = 2)
    private BigDecimal grade;

    @Column(name = "comments", length = 2000)
    private String comments;

    public Integer getId() {
        return this.id;
    }

    public Apprentice getApprentice() {
        return this.apprentice;
    }

    public String getSubject() {
        return this.subject;
    }

    public BigDecimal getGrade() {
        return this.grade;
    }

    public String getComments() {
        return this.comments;
    }
}