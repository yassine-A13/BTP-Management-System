package ma.btpmanagement.dtos.entreprise;

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
public class EntrepriseResponseDTO {

    private UUID id;
    private String raisonSociale;
    private String ice;
    private String ifNumber;
    private String patente;
    private String email;
    private String telephone;
    private String adresse;
    private String ville;
    private String logo;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
