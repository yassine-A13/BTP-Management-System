package ma.btpmanagement.dtos.facture;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.btpmanagement.enums.StatutFacture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureFournisseurRequestDTO {

    @NotBlank
    private String numeroFacture;

    @NotNull
    @PastOrPresent
    private LocalDate dateFacture;

    private String natureAchat;

    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal montantHT;

    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal montantTVA;

    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal montantTTC;

    private String observation;

    private StatutFacture statut;

    @Builder.Default
    private Boolean active = true;

    @NotNull
    private UUID fournisseurId;

    @NotNull
    private UUID projetId;
}
