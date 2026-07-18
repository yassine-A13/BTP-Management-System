package ma.btpmanagement.dtos.ligneattachement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneAttachementResponseDTO {

    private UUID id;
    private String designation;
    private String unite;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal montant;
    private String observation;
    private UUID attachementId;
    private String numeroAttachement;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
