package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.facture.FactureFournisseurRequestDTO;
import ma.btpmanagement.dtos.facture.FactureFournisseurResponseDTO;
import ma.btpmanagement.entites.FactureFournisseur;
import ma.btpmanagement.entites.Fournisseur;
import ma.btpmanagement.entites.Projet;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.FactureFournisseurMapper;
import ma.btpmanagement.repositories.FactureFournisseurRepository;
import ma.btpmanagement.repositories.FournisseurRepository;
import ma.btpmanagement.repositories.ProjetRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FactureFournisseurServiceImpl implements FactureFournisseurService {

    private final FactureFournisseurRepository factureFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ProjetRepository projetRepository;
    private final FactureFournisseurMapper factureFournisseurMapper;

    @Override
    public FactureFournisseurResponseDTO create(FactureFournisseurRequestDTO dto) {
        var fournisseur = findFournisseurById(dto.getFournisseurId());
        var projet = findProjetById(dto.getProjetId());

        var facture = factureFournisseurMapper.toEntity(dto);
        if (facture.getActive() == null) {
            facture.setActive(true);
        }
        facture.setFournisseur(fournisseur);
        facture.setProjet(projet);
        var savedFacture = factureFournisseurRepository.save(facture);

        return factureFournisseurMapper.toResponseDTO(savedFacture);
    }

    @Override
    public FactureFournisseurResponseDTO update(UUID id, FactureFournisseurRequestDTO dto) {
        var facture = findFactureById(id);
        var fournisseur = facture.getFournisseur();
        var projet = facture.getProjet();

        if (!fournisseur.getId().equals(dto.getFournisseurId())) {
            fournisseur = findFournisseurById(dto.getFournisseurId());
        }

        if (!projet.getId().equals(dto.getProjetId())) {
            projet = findProjetById(dto.getProjetId());
        }

        factureFournisseurMapper.updateEntityFromDTO(dto, facture);
        facture.setFournisseur(fournisseur);
        facture.setProjet(projet);

        var updatedFacture = factureFournisseurRepository.save(facture);
        return factureFournisseurMapper.toResponseDTO(updatedFacture);
    }

    @Override
    public FactureFournisseurResponseDTO getById(UUID id) {
        return factureFournisseurMapper.toResponseDTO(findFactureById(id));
    }

    @Override
    public List<FactureFournisseurResponseDTO> getAll() {
        return factureFournisseurMapper.toResponseDTOList(factureFournisseurRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        factureFournisseurRepository.delete(findFactureById(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return factureFournisseurRepository.existsById(id);
    }

    private FactureFournisseur findFactureById(UUID id) {
        return factureFournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Facture fournisseur introuvable avec l'identifiant : " + id));
    }

    private Fournisseur findFournisseurById(UUID id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fournisseur introuvable avec l'identifiant : " + id));
    }

    private Projet findProjetById(UUID id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projet introuvable avec l'identifiant : " + id));
    }
}
