package ma.btpmanagement.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "lignes_attachements")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = "attachement")
public class LigneAttachement {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "unite")
    private String unite;

    @Column(name = "quantite", precision = 19, scale = 3)
    private BigDecimal quantite;

    @Column(name = "prix_unitaire", precision = 19, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(name = "montant", precision = 19, scale = 2)
    private BigDecimal montant;

    @Column(name = "observation", columnDefinition = "text")
    private String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachement_id", nullable = false)
    private Attachement attachement;
}
