package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.projet.ProjetRequestDTO;
import ma.btpmanagement.dtos.projet.ProjetResponseDTO;
import ma.btpmanagement.entites.Projet;
import ma.btpmanagement.enums.StatutProjet;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.ProjetMapper;
import ma.btpmanagement.repositories.EntrepriseRepository;
import ma.btpmanagement.repositories.ProjetRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjetServiceImpl implements ProjetService {

    private final ProjetRepository projetRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final ProjetMapper projetMapper;

    @Override
    public ProjetResponseDTO create(ProjetRequestDTO dto) {
        var entreprise = entrepriseRepository.findById(dto.getEntrepriseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entreprise introuvable avec l'identifiant : " + dto.getEntrepriseId()));

        var projet = projetMapper.toEntity(dto);
        if (projet.getActive() == null) {
            projet.setActive(true);
        }
        if (projet.getStatut() == null) {
            projet.setStatut(StatutProjet.PLANIFIE);
        }
        projet.setEntreprise(entreprise);
        var savedProjet = projetRepository.save(projet);

        return projetMapper.toResponseDTO(savedProjet);
    }

    @Override
    public ProjetResponseDTO update(UUID id, ProjetRequestDTO dto) {
        var projet = findProjetById(id);

        if (!projet.getEntreprise().getId().equals(dto.getEntrepriseId())) {
            var entreprise = entrepriseRepository.findById(dto.getEntrepriseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Entreprise introuvable avec l'identifiant : " + dto.getEntrepriseId()));
            projet.setEntreprise(entreprise);
        }

        projetMapper.updateEntityFromDTO(dto, projet);
        var updatedProjet = projetRepository.save(projet);

        return projetMapper.toResponseDTO(updatedProjet);
    }

    @Override
    public ProjetResponseDTO getById(UUID id) {
        return projetMapper.toResponseDTO(findProjetById(id));
    }

    @Override
    public List<ProjetResponseDTO> getAll() {
        return projetMapper.toResponseDTOList(projetRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        projetRepository.delete(findProjetById(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return projetRepository.existsById(id);
    }

    private Projet findProjetById(UUID id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projet introuvable avec l'identifiant : " + id));
    }
}
