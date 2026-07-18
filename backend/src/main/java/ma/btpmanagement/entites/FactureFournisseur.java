package ma.btpmanagement.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.btpmanagement.enums.StatutFacture;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "factures_fournisseurs")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"projet", "fournisseur"})
public class FactureFournisseur {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @NotBlank
    @Column(name = "numero_facture", nullable = false, unique = true)
    private String numeroFacture;

    @Column(name = "date_facture")
    private LocalDate dateFacture;

    @Column(name = "nature_achat")
    private String natureAchat;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "montant_ht", precision = 19, scale = 2)
    private BigDecimal montantHT;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "montant_tva", precision = 19, scale = 2)
    private BigDecimal montantTVA;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "montant_ttc", precision = 19, scale = 2)
    private BigDecimal montantTTC;

    @Column(name = "observation", columnDefinition = "text")
    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutFacture statut;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;
}
