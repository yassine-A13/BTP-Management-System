package ma.btpmanagement.dtos.entreprise;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class EntrepriseRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String raisonSociale;

    @Size(max = 255)
    private String ice;

    @Size(max = 255)
    private String ifNumber;

    @Size(max = 255)
    private String patente;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String telephone;

    @Size(max = 255)
    private String adresse;

    @Size(max = 255)
    private String ville;

    @Size(max = 255)
    private String logo;

    @Builder.Default
    private Boolean active = true;
}
