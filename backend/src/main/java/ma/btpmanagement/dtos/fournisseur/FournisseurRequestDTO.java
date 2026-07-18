package ma.btpmanagement.dtos.fournisseur;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FournisseurRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String raisonSociale;

    @Size(max = 255)
    private String ice;

    @Size(max = 255)
    private String ifNumber;

    @Size(max = 255)
    private String patente;

    @Size(max = 255)
    private String contact;

    @Pattern(regexp = "^\\+?[0-9][0-9 ()-]{7,19}$")
    @Size(max = 20)
    private String telephone;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String adresse;

    @Size(max = 255)
    private String ville;

    @Builder.Default
    private Boolean active = true;

    @NotNull
    private UUID entrepriseId;
}
