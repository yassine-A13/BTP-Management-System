package ma.btpmanagement.dtos.fournisseur;

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
public class FournisseurResponseDTO {

    private UUID id;
    private String raisonSociale;
    private String ice;
    private String ifNumber;
    private String patente;
    private String contact;
    private String telephone;
    private String email;
    private String adresse;
    private String ville;
    private Boolean active;
    private UUID entrepriseId;
    private String entrepriseNom;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
