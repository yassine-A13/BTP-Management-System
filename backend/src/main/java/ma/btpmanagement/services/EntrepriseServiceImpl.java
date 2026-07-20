package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.entreprise.EntrepriseRequestDTO;
import ma.btpmanagement.dtos.entreprise.EntrepriseResponseDTO;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.EntrepriseMapper;
import ma.btpmanagement.repositories.EntrepriseRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final EntrepriseMapper entrepriseMapper;

    @Override
    public EntrepriseResponseDTO create(EntrepriseRequestDTO dto) {
        var entreprise = entrepriseMapper.toEntity(dto);
        var savedEntreprise = entrepriseRepository.save(entreprise);

        return entrepriseMapper.toResponseDTO(savedEntreprise);
    }

    @Override
    public EntrepriseResponseDTO update(UUID id, EntrepriseRequestDTO dto) {
        var entreprise = findEntrepriseById(id);
        entrepriseMapper.updateEntityFromDTO(dto, entreprise);
        var updatedEntreprise = entrepriseRepository.save(entreprise);

        return entrepriseMapper.toResponseDTO(updatedEntreprise);
    }

    @Override
    public EntrepriseResponseDTO getById(UUID id) {
        return entrepriseMapper.toResponseDTO(findEntrepriseById(id));
    }

    @Override
    public List<EntrepriseResponseDTO> getAll() {
        return entrepriseMapper.toResponseDTOList(entrepriseRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        var entreprise = findEntrepriseById(id);
        entrepriseRepository.delete(entreprise);
    }

    @Override
    public boolean existsById(UUID id) {
        return entrepriseRepository.existsById(id);
    }

    private ma.btpmanagement.entites.Entreprise findEntrepriseById(UUID id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entreprise introuvable avec l'identifiant : " + id));
    }
}
