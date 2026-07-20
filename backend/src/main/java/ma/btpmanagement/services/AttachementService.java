package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.attachement.AttachementRequestDTO;
import ma.btpmanagement.dtos.attachement.AttachementResponseDTO;

public interface AttachementService {

    AttachementResponseDTO create(AttachementRequestDTO dto);

    AttachementResponseDTO update(UUID id, AttachementRequestDTO dto);

    AttachementResponseDTO getById(UUID id);

    List<AttachementResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
