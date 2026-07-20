package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.entreprise.EntrepriseRequestDTO;
import ma.btpmanagement.dtos.entreprise.EntrepriseResponseDTO;

public interface EntrepriseService {

    EntrepriseResponseDTO create(EntrepriseRequestDTO dto);

    EntrepriseResponseDTO update(UUID id, EntrepriseRequestDTO dto);

    EntrepriseResponseDTO getById(UUID id);

    List<EntrepriseResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
