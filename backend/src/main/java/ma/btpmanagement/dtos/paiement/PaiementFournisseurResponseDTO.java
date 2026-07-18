package ma.btpmanagement.dtos.paiement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.btpmanagement.enums.ModePaiement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementFournisseurResponseDTO {

    private UUID id;
    private BigDecimal montant;
    private LocalDate datePaiement;
    private ModePaiement modePaiement;
    private String referencePaiement;
    private String observation;
    private Boolean active;
    private UUID factureFournisseurId;
    private String numeroFacture;
    private BigDecimal montantFacture;
    private BigDecimal montantPaye;
    private BigDecimal resteAPayer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
