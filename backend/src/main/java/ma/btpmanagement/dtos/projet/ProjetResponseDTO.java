package ma.btpmanagement.dtos.projet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.btpmanagement.enums.StatutProjet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetResponseDTO {

    private UUID id;
    private String reference;
    private String nom;
    private String description;
    private String client;
    private String adresse;
    private String ville;
    private BigDecimal budgetPrevisionnel;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;
    private StatutProjet statut;
    private Boolean active;
    private UUID entrepriseId;
    private String entrepriseNom;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
