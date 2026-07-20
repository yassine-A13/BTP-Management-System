package ma.btpmanagement.mappers;

import java.util.List;

import ma.btpmanagement.dtos.ligneattachement.LigneAttachementRequestDTO;
import ma.btpmanagement.dtos.ligneattachement.LigneAttachementResponseDTO;
import ma.btpmanagement.entites.LigneAttachement;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LigneAttachementMapper {

    @Mapping(target = "attachement", ignore = true)
    LigneAttachement toEntity(LigneAttachementRequestDTO dto);

    @Mapping(target = "attachementId", source = "attachement.id")
    @Mapping(target = "numeroAttachement", source = "attachement.numero")
    LigneAttachementResponseDTO toResponseDTO(LigneAttachement entity);

    List<LigneAttachementResponseDTO> toResponseDTOList(List<LigneAttachement> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attachement", ignore = true)
    void updateEntityFromDTO(
            LigneAttachementRequestDTO dto,
            @MappingTarget LigneAttachement entity
    );
}
