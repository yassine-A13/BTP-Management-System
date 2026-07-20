package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.projet.ProjetRequestDTO;
import ma.btpmanagement.dtos.projet.ProjetResponseDTO;

public interface ProjetService {

    ProjetResponseDTO create(ProjetRequestDTO dto);

    ProjetResponseDTO update(UUID id, ProjetRequestDTO dto);

    ProjetResponseDTO getById(UUID id);

    List<ProjetResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
