package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.paiement.PaiementFournisseurRequestDTO;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurResponseDTO;

public interface PaiementFournisseurService {

    PaiementFournisseurResponseDTO create(PaiementFournisseurRequestDTO dto);

    PaiementFournisseurResponseDTO update(UUID id, PaiementFournisseurRequestDTO dto);

    PaiementFournisseurResponseDTO getById(UUID id);

    List<PaiementFournisseurResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
