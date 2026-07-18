package ma.btpmanagement.dtos.facture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.btpmanagement.enums.StatutFacture;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureFournisseurResponseDTO {

    private UUID id;
    private String numeroFacture;
    private LocalDate dateFacture;
    private String natureAchat;
    private BigDecimal montantHT;
    private BigDecimal montantTVA;
    private BigDecimal montantTTC;
    private String observation;
    private StatutFacture statut;
    private Boolean active;
    private UUID fournisseurId;
    private String fournisseurNom;
    private UUID projetId;
    private String projetNom;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
