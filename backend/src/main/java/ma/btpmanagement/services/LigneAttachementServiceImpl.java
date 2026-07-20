package ma.btpmanagement.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.ligneattachement.LigneAttachementRequestDTO;
import ma.btpmanagement.dtos.ligneattachement.LigneAttachementResponseDTO;
import ma.btpmanagement.entites.Attachement;
import ma.btpmanagement.entites.LigneAttachement;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.LigneAttachementMapper;
import ma.btpmanagement.repositories.AttachementRepository;
import ma.btpmanagement.repositories.LigneAttachementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LigneAttachementServiceImpl implements LigneAttachementService {

    private final LigneAttachementRepository ligneAttachementRepository;
    private final AttachementRepository attachementRepository;
    private final LigneAttachementMapper ligneAttachementMapper;

    @Override
    public LigneAttachementResponseDTO create(LigneAttachementRequestDTO dto) {
        var attachement = findAttachementById(dto.getAttachementId());
        var ligne = ligneAttachementMapper.toEntity(dto);
        ligne.setAttachement(attachement);

        var savedLigne = ligneAttachementRepository.save(ligne);
        recalculateAttachementTotal(attachement);

        return ligneAttachementMapper.toResponseDTO(savedLigne);
    }

    @Override
    public LigneAttachementResponseDTO update(UUID id, LigneAttachementRequestDTO dto) {
        var ligne = findLigneById(id);
        var ancienAttachement = ligne.getAttachement();
        var nouvelAttachement = ancienAttachement;

        if (!ancienAttachement.getId().equals(dto.getAttachementId())) {
            nouvelAttachement = findAttachementById(dto.getAttachementId());
        }

        ligneAttachementMapper.updateEntityFromDTO(dto, ligne);
        ligne.setAttachement(nouvelAttachement);

        var updatedLigne = ligneAttachementRepository.save(ligne);
        recalculateAttachementTotal(ancienAttachement);
        if (!ancienAttachement.getId().equals(nouvelAttachement.getId())) {
            recalculateAttachementTotal(nouvelAttachement);
        }

        return ligneAttachementMapper.toResponseDTO(updatedLigne);
    }

    @Override
    @Transactional(readOnly = true)
    public LigneAttachementResponseDTO getById(UUID id) {
        return ligneAttachementMapper.toResponseDTO(findLigneById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneAttachementResponseDTO> getAll() {
        return ligneAttachementMapper.toResponseDTOList(ligneAttachementRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        var ligne = findLigneById(id);
        var attachement = ligne.getAttachement();

        ligneAttachementRepository.delete(ligne);
        ligneAttachementRepository.flush();
        recalculateAttachementTotal(attachement);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return ligneAttachementRepository.existsById(id);
    }

    private LigneAttachement findLigneById(UUID id) {
        return ligneAttachementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ligne d'attachement introuvable avec l'identifiant : " + id));
    }

    private Attachement findAttachementById(UUID id) {
        return attachementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Attachement introuvable avec l'identifiant : " + id));
    }

    private void recalculateAttachementTotal(Attachement attachement) {
        var montantTotal = ligneAttachementRepository.findAll().stream()
                .filter(ligne -> ligne.getAttachement() != null)
                .filter(ligne -> attachement.getId().equals(ligne.getAttachement().getId()))
                .map(LigneAttachement::getMontant)
                .filter(montant -> montant != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        attachement.setMontantTotal(montantTotal);
        attachementRepository.save(attachement);
    }
}
