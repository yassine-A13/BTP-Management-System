package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import ma.btpmanagement.dtos.facture.FactureFournisseurRequestDTO;
import ma.btpmanagement.dtos.facture.FactureFournisseurResponseDTO;

public interface FactureFournisseurService {

    FactureFournisseurResponseDTO create(FactureFournisseurRequestDTO dto);

    FactureFournisseurResponseDTO update(UUID id, FactureFournisseurRequestDTO dto);

    FactureFournisseurResponseDTO getById(UUID id);

    List<FactureFournisseurResponseDTO> getAll();

    void delete(UUID id);

    boolean existsById(UUID id);
}
