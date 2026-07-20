package ma.btpmanagement.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.attachement.AttachementRequestDTO;
import ma.btpmanagement.dtos.attachement.AttachementResponseDTO;
import ma.btpmanagement.entites.Attachement;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.AttachementMapper;
import ma.btpmanagement.repositories.AttachementRepository;
import ma.btpmanagement.repositories.ProjetRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachementServiceImpl implements AttachementService {

    private final AttachementRepository attachementRepository;
    private final ProjetRepository projetRepository;
    private final AttachementMapper attachementMapper;

    @Override
    public AttachementResponseDTO create(AttachementRequestDTO dto) {
        var projet = findProjetById(dto.getProjetId());
        var attachement = attachementMapper.toEntity(dto);
        attachement.setProjet(projet);

        var savedAttachement = attachementRepository.save(attachement);
        return attachementMapper.toResponseDTO(savedAttachement);
    }

    @Override
    public AttachementResponseDTO update(UUID id, AttachementRequestDTO dto) {
        var attachement = findAttachementById(id);
        var projet = attachement.getProjet();

        if (!projet.getId().equals(dto.getProjetId())) {
            projet = findProjetById(dto.getProjetId());
        }

        attachementMapper.updateEntityFromDTO(dto, attachement);
        attachement.setProjet(projet);

        var updatedAttachement = attachementRepository.save(attachement);
        return attachementMapper.toResponseDTO(updatedAttachement);
    }

    @Override
    public AttachementResponseDTO getById(UUID id) {
        return attachementMapper.toResponseDTO(findAttachementById(id));
    }

    @Override
    public List<AttachementResponseDTO> getAll() {
        return attachementMapper.toResponseDTOList(attachementRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        attachementRepository.delete(findAttachementById(id));
    }

    @Override
    public boolean existsById(UUID id) {
        return attachementRepository.existsById(id);
    }

    private Attachement findAttachementById(UUID id) {
        return attachementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Attachement introuvable avec l'identifiant : " + id));
    }

    private ma.btpmanagement.entites.Projet findProjetById(UUID id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Projet introuvable avec l'identifiant : " + id));
    }
}
