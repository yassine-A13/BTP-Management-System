package ma.btpmanagement.dtos.attachement;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachementRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String numero;

    @NotNull
    @PastOrPresent
    private LocalDate dateAttachement;

    @DecimalMin(value = "0.0", inclusive = true)
    @Positive
    private BigDecimal montantTotal;

    @Size(max = 2000)
    private String observation;

    @Builder.Default
    private Boolean valide = false;

    @Builder.Default
    private Boolean active = true;

    @NotNull
    private UUID projetId;
}
