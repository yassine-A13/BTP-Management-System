package ma.btpmanagement.mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurRequestDTO;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurResponseDTO;
import ma.btpmanagement.entites.FactureFournisseur;
import ma.btpmanagement.entites.PaiementFournisseur;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class PaiementFournisseurMapper {

    @Mappings({
            @Mapping(target = "factureFournisseur", source = "factureFournisseurId"),
            @Mapping(target = "active", ignore = true)
    })
    public abstract PaiementFournisseur toEntity(PaiementFournisseurRequestDTO dto);

    @Mappings({
            @Mapping(target = "factureFournisseurId", source = "factureFournisseur.id"),
            @Mapping(target = "numeroFacture", source = "factureFournisseur.numeroFacture"),
            @Mapping(target = "montantFacture", source = "factureFournisseur.montantTTC"),
            @Mapping(target = "montantPaye", source = "montant"),
            @Mapping(target = "resteAPayer", expression = "java(calculerResteAPayer(entity))")
    })
    public abstract PaiementFournisseurResponseDTO toResponseDTO(PaiementFournisseur entity);

    public abstract List<PaiementFournisseurResponseDTO> toResponseDTOList(
            List<PaiementFournisseur> entities
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "factureFournisseur", source = "factureFournisseurId")
    })
    public abstract void updateEntityFromDTO(
            PaiementFournisseurRequestDTO dto,
            @MappingTarget PaiementFournisseur entity
    );

    protected FactureFournisseur map(UUID id) {
        if (id == null) {
            return null;
        }

        return FactureFournisseur.builder()
                .id(id)
                .build();
    }

    protected BigDecimal calculerResteAPayer(PaiementFournisseur paiement) {
        if (paiement == null
                || paiement.getFactureFournisseur() == null
                || paiement.getFactureFournisseur().getMontantTTC() == null
                || paiement.getMontant() == null) {
            return null;
        }

        return paiement.getFactureFournisseur().getMontantTTC()
                .subtract(paiement.getMontant())
                .max(BigDecimal.ZERO);
    }
}
