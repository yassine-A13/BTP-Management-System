package ma.btpmanagement.mappers;

import java.util.List;
import ma.btpmanagement.dtos.entreprise.EntrepriseRequestDTO;
import ma.btpmanagement.dtos.entreprise.EntrepriseResponseDTO;
import ma.btpmanagement.entites.Entreprise;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {

    Entreprise toEntity(EntrepriseRequestDTO dto);

    EntrepriseResponseDTO toResponseDTO(Entreprise entity);

    List<EntrepriseResponseDTO> toResponseDTOList(List<Entreprise> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(EntrepriseRequestDTO dto, @MappingTarget Entreprise entity);
}
