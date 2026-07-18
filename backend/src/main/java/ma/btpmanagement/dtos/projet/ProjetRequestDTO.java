package ma.btpmanagement.dtos.projet;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.btpmanagement.enums.StatutProjet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetRequestDTO {

    @Size(max = 255)
    private String reference;

    @NotBlank
    @Size(max = 255)
    private String nom;

    @Size(max = 2000)
    private String description;

    @Size(max = 255)
    private String client;

    @Size(max = 255)
    private String adresse;

    @Size(max = 255)
    private String ville;

    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal budgetPrevisionnel;

    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private LocalDate dateFinReelle;

    @NotNull
    private StatutProjet statut;

    @Builder.Default
    private Boolean active = true;

    @NotNull
    private UUID entrepriseId;
}
