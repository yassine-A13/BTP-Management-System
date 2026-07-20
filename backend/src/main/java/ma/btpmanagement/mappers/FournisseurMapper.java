package ma.btpmanagement.mappers;

import java.util.List;
import ma.btpmanagement.dtos.fournisseur.FournisseurRequestDTO;
import ma.btpmanagement.dtos.fournisseur.FournisseurResponseDTO;
import ma.btpmanagement.entites.Fournisseur;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FournisseurMapper {

    @Mapping(target = "entreprise", ignore = true)
    Fournisseur toEntity(FournisseurRequestDTO dto);

    @Mapping(target = "entrepriseId", source = "entreprise.id")
    @Mapping(target = "entrepriseNom", source = "entreprise.raisonSociale")
    FournisseurResponseDTO toResponseDTO(Fournisseur entity);

    List<FournisseurResponseDTO> toResponseDTOList(List<Fournisseur> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "entreprise", ignore = true)
    void updateEntityFromDTO(FournisseurRequestDTO dto, @MappingTarget Fournisseur entity);
}
