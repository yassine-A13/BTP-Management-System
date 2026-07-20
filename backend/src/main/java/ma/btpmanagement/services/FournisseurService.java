package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.fournisseur.FournisseurRequestDTO;
import ma.btpmanagement.dtos.fournisseur.FournisseurResponseDTO;

public interface FournisseurService {

    FournisseurResponseDTO create(FournisseurRequestDTO dto);

    FournisseurResponseDTO update(UUID id, FournisseurRequestDTO dto);

    FournisseurResponseDTO getById(UUID id);

    List<FournisseurResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
