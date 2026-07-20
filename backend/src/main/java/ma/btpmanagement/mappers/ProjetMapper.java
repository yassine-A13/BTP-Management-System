package ma.btpmanagement.mappers;

import java.util.List;
import ma.btpmanagement.dtos.projet.ProjetRequestDTO;
import ma.btpmanagement.dtos.projet.ProjetResponseDTO;
import ma.btpmanagement.entites.Projet;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProjetMapper {

    @Mapping(target = "entreprise", ignore = true)
    Projet toEntity(ProjetRequestDTO dto);

    @Mapping(target = "entrepriseId", source = "entreprise.id")
    @Mapping(target = "entrepriseNom", source = "entreprise.raisonSociale")
    ProjetResponseDTO toResponseDTO(Projet entity);

    List<ProjetResponseDTO> toResponseDTOList(List<Projet> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "entreprise", ignore = true)
    void updateEntityFromDTO(ProjetRequestDTO dto, @MappingTarget Projet entity);
}
