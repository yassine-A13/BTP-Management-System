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
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.btpmanagement.enums.ModePaiement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "paiements_fournisseurs")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = "factureFournisseur")
public class PaiementFournisseur {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "montant", nullable = false, precision = 19, scale = 2)
    private BigDecimal montant;

    @NotNull
    @Column(name = "date_paiement", nullable = false)
    private LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_paiement")
    private ModePaiement modePaiement;

    @Column(name = "reference_paiement")
    private String referencePaiement;

    @Column(name = "observation", columnDefinition = "text")
    private String observation;

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
    @JoinColumn(name = "facture_fournisseur_id", nullable = false)
    private FactureFournisseur factureFournisseur;
}
