package ma.btpmanagement.dtos.attachement;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class AttachementResponseDTO {

    private UUID id;
    private String numero;
    private LocalDate dateAttachement;
    private BigDecimal montantTotal;
    private String observation;
    private Boolean valide;
    private Boolean active;
    private UUID projetId;
    private String projetNom;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
