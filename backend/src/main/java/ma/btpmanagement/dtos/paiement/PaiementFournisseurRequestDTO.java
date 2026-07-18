package ma.btpmanagement.dtos.paiement;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.btpmanagement.enums.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementFournisseurRequestDTO {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Positive
    private BigDecimal montant;

    @NotNull
    @PastOrPresent
    private LocalDate datePaiement;

    private ModePaiement modePaiement;
    private String referencePaiement;
    private String observation;

    @Builder.Default
    private Boolean active = true;

    @NotNull
    private UUID factureFournisseurId;
}
