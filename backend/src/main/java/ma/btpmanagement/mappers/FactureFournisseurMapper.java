package ma.btpmanagement.mappers;

import java.util.List;
import java.util.UUID;
import ma.btpmanagement.dtos.facture.FactureFournisseurRequestDTO;
import ma.btpmanagement.dtos.facture.FactureFournisseurResponseDTO;
import ma.btpmanagement.entites.FactureFournisseur;
import ma.btpmanagement.entites.Fournisseur;
import ma.btpmanagement.entites.Projet;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class FactureFournisseurMapper {

    @Mappings({
            @Mapping(target = "fournisseur", source = "fournisseurId"),
            @Mapping(target = "projet", source = "projetId")
    })
    public abstract FactureFournisseur toEntity(FactureFournisseurRequestDTO dto);

    @Mappings({
            @Mapping(target = "fournisseurId", source = "fournisseur.id"),
            @Mapping(target = "fournisseurNom", source = "fournisseur.raisonSociale"),
            @Mapping(target = "projetId", source = "projet.id"),
            @Mapping(target = "projetNom", source = "projet.nom")
    })
    public abstract FactureFournisseurResponseDTO toResponseDTO(FactureFournisseur entity);

    public abstract List<FactureFournisseurResponseDTO> toResponseDTOList(
            List<FactureFournisseur> entities
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "fournisseur", source = "fournisseurId"),
            @Mapping(target = "projet", source = "projetId")
    })
    public abstract void updateEntityFromDTO(
            FactureFournisseurRequestDTO dto,
            @MappingTarget FactureFournisseur entity
    );

    protected Fournisseur map(UUID id) {
        if (id == null) {
            return null;
        }

        return Fournisseur.builder()
                .id(id)
                .build();
    }

    protected Projet mapProjet(UUID id) {
        if (id == null) {
            return null;
        }

        return Projet.builder()
                .id(id)
                .build();
    }
}
