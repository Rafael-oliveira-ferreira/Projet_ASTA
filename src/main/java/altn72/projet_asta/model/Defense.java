package altn72.projet_asta.model;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Entity
@Table(name = "defense")
public class Defense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defense_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @Column(name = "defense_date")
    private LocalDate defenseDate;

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

    public LocalDate getDefenseDate() {
        return this.defenseDate;
    }

    public BigDecimal getGrade() {
        return this.grade;
    }

    public String getComments() {
        return this.comments;
    }
}