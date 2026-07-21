package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.fournisseur.FournisseurRequestDTO;
import ma.btpmanagement.dtos.fournisseur.FournisseurResponseDTO;
import ma.btpmanagement.entites.Fournisseur;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.FournisseurMapper;
import ma.btpmanagement.repositories.EntrepriseRepository;
import ma.btpmanagement.repositories.FournisseurRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final FournisseurMapper fournisseurMapper;

    @Override
    public FournisseurResponseDTO create(FournisseurRequestDTO dto) {
        var entreprise = entrepriseRepository.findById(dto.getEntrepriseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entreprise introuvable avec l'identifiant : " + dto.getEntrepriseId()));

        var fournisseur = fournisseurMapper.toEntity(dto);
        if (fournisseur.getActive() == null) {
            fournisseur.setActive(true);
        }
        fournisseur.setEntreprise(entreprise);
        var savedFournisseur = fournisseurRepository.save(fournisseur);

        return fournisseurMapper.toResponseDTO(savedFournisseur);
    }

    @Override
    public FournisseurResponseDTO update(UUID id, FournisseurRequestDTO dto) {
        var fournisseur = findFournisseurById(id);

        if (!fournisseur.getEntreprise().getId().equals(dto.getEntrepriseId())) {
            var entreprise = entrepriseRepository.findById(dto.getEntrepriseId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Entreprise introuvable avec l'identifiant : " + dto.getEntrepriseId()));
            fournisseur.setEntreprise(entreprise);
        }

        fournisseurMapper.updateEntityFromDTO(dto, fournisseur);
        var updatedFournisseur = fournisseurRepository.save(fournisseur);

        return fournisseurMapper.toResponseDTO(updatedFournisseur);
    }

    @Override
    public FournisseurResponseDTO getById(UUID id) {
        return fournisseurMapper.toResponseDTO(findFournisseurById(id));
    }

    @Override
    public List<FournisseurResponseDTO> getAll() {
        return fournisseurMapper.toResponseDTOList(fournisseurRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        fournisseurRepository.delete(findFournisseurById(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return fournisseurRepository.existsById(id);
    }

    private Fournisseur findFournisseurById(UUID id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fournisseur introuvable avec l'identifiant : " + id));
    }
}
