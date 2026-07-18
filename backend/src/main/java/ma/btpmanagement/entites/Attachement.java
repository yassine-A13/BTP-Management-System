package ma.btpmanagement.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "attachements")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"projet", "lignes"})
public class Attachement {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "numero", unique = true)
    private String numero;

    @Column(name = "date_attachement")
    private LocalDate dateAttachement;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "montant_total", precision = 19, scale = 2)
    private BigDecimal montantTotal;

    @Column(name = "observation", columnDefinition = "text")
    private String observation;

    @Column(name = "valide", nullable = false, columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean valide = false;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;

    @OneToMany(
            mappedBy = "attachement",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<LigneAttachement> lignes = new ArrayList<>();

    public void addLigne(LigneAttachement ligne) {
        lignes.add(ligne);
        ligne.setAttachement(this);
    }

    public void removeLigne(LigneAttachement ligne) {
        lignes.remove(ligne);
        ligne.setAttachement(null);
    }
}
