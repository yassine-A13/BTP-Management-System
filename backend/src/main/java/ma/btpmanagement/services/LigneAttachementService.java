package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.ligneattachement.LigneAttachementRequestDTO;
import ma.btpmanagement.dtos.ligneattachement.LigneAttachementResponseDTO;

public interface LigneAttachementService {

    LigneAttachementResponseDTO create(LigneAttachementRequestDTO dto);

    LigneAttachementResponseDTO update(UUID id, LigneAttachementRequestDTO dto);

    LigneAttachementResponseDTO getById(UUID id);

    List<LigneAttachementResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
