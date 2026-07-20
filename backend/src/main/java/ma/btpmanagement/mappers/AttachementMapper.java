package ma.btpmanagement.mappers;

import java.util.List;

import ma.btpmanagement.dtos.attachement.AttachementRequestDTO;
import ma.btpmanagement.dtos.attachement.AttachementResponseDTO;
import ma.btpmanagement.entites.Attachement;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AttachementMapper {

    @Mapping(target = "projet", ignore = true)
    Attachement toEntity(AttachementRequestDTO dto);

    @Mapping(target = "projetId", source = "projet.id")
    @Mapping(target = "projetNom", source = "projet.nom")
    AttachementResponseDTO toResponseDTO(Attachement entity);

    List<AttachementResponseDTO> toResponseDTOList(List<Attachement> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "projet", ignore = true)
    void updateEntityFromDTO(
            AttachementRequestDTO dto,
            @MappingTarget Attachement entity
    );
}
