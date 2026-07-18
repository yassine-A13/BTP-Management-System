package ma.btpmanagement.dtos.ligneattachement;

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

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneAttachementRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String designation;

    @NotBlank
    @Size(max = 100)
    private String unite;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal quantite;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal prixUnitaire;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal montant;

    @Size(max = 2000)
    private String observation;

    @NotNull
    private UUID attachementId;
}
