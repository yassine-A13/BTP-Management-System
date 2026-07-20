package ma.btpmanagement.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurRequestDTO;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurResponseDTO;
import ma.btpmanagement.entites.FactureFournisseur;
import ma.btpmanagement.entites.PaiementFournisseur;
import ma.btpmanagement.enums.StatutFacture;
import ma.btpmanagement.exceptions.ResourceNotFoundException;
import ma.btpmanagement.mappers.PaiementFournisseurMapper;
import ma.btpmanagement.repositories.FactureFournisseurRepository;
import ma.btpmanagement.repositories.PaiementFournisseurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaiementFournisseurServiceImpl implements PaiementFournisseurService {

    private final PaiementFournisseurRepository paiementFournisseurRepository;
    private final FactureFournisseurRepository factureFournisseurRepository;
    private final PaiementFournisseurMapper paiementFournisseurMapper;

    @Override
    public PaiementFournisseurResponseDTO create(PaiementFournisseurRequestDTO dto) {
        validateMontant(dto.getMontant());
        var facture = findFactureById(dto.getFactureFournisseurId());
        validateMontantDisponible(facture, dto.getMontant(), null);

        var paiement = paiementFournisseurMapper.toEntity(dto);
        paiement.setFactureFournisseur(facture);
        var savedPaiement = paiementFournisseurRepository.save(paiement);
        updateFactureStatus(facture);

        return paiementFournisseurMapper.toResponseDTO(savedPaiement);
    }

    @Override
    public PaiementFournisseurResponseDTO update(UUID id, PaiementFournisseurRequestDTO dto) {
        validateMontant(dto.getMontant());
        var paiement = findPaiementById(id);
        var ancienneFacture = paiement.getFactureFournisseur();
        var nouvelleFacture = ancienneFacture;

        if (!ancienneFacture.getId().equals(dto.getFactureFournisseurId())) {
            nouvelleFacture = findFactureById(dto.getFactureFournisseurId());
        }

        validateMontantDisponible(nouvelleFacture, dto.getMontant(),
                nouvelleFacture.getId().equals(ancienneFacture.getId()) ? id : null);

        paiementFournisseurMapper.updateEntityFromDTO(dto, paiement);
        paiement.setFactureFournisseur(nouvelleFacture);
        var updatedPaiement = paiementFournisseurRepository.save(paiement);

        updateFactureStatus(ancienneFacture);
        if (!ancienneFacture.getId().equals(nouvelleFacture.getId())) {
            updateFactureStatus(nouvelleFacture);
        }

        return paiementFournisseurMapper.toResponseDTO(updatedPaiement);
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementFournisseurResponseDTO getById(UUID id) {
        return paiementFournisseurMapper.toResponseDTO(findPaiementById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementFournisseurResponseDTO> getAll() {
        return paiementFournisseurMapper.toResponseDTOList(paiementFournisseurRepository.findAll());
    }

    @Override
    public void delete(UUID id) {
        var paiement = findPaiementById(id);
        var facture = paiement.getFactureFournisseur();

        paiementFournisseurRepository.delete(paiement);
        paiementFournisseurRepository.flush();
        updateFactureStatus(facture);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(UUID id) {
        return paiementFournisseurRepository.existsById(id);
    }

    private PaiementFournisseur findPaiementById(UUID id) {
        return paiementFournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paiement fournisseur introuvable avec l'identifiant : " + id));
    }

    private FactureFournisseur findFactureById(UUID id) {
        return factureFournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Facture fournisseur introuvable avec l'identifiant : " + id));
    }

    private void validateMontant(BigDecimal montant) {
        if (montant == null || montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant du paiement doit être supérieur à zéro");
        }
    }

    private void validateMontantDisponible(
            FactureFournisseur facture,
            BigDecimal montant,
            UUID paiementExcluId
    ) {
        if (facture.getMontantTTC() == null) {
            throw new IllegalArgumentException("Le montant TTC de la facture est obligatoire");
        }

        var montantPaye = calculateMontantPaye(facture, paiementExcluId);
        var resteAPayer = facture.getMontantTTC().subtract(montantPaye);
        if (montant.compareTo(resteAPayer) > 0) {
            throw new IllegalArgumentException(
                    "Le montant du paiement dépasse le reste à payer de la facture");
        }
    }

    private void updateFactureStatus(FactureFournisseur facture) {
        var montantPaye = calculateMontantPaye(facture, null);
        var montantTTC = facture.getMontantTTC();

        if (montantPaye.signum() == 0) {
            facture.setStatut(StatutFacture.NON_PAYEE);
        } else if (montantTTC != null && montantPaye.compareTo(montantTTC) >= 0) {
            facture.setStatut(StatutFacture.PAYEE);
        } else {
            facture.setStatut(StatutFacture.PARTIELLEMENT_PAYEE);
        }

        factureFournisseurRepository.save(facture);
    }

    private BigDecimal calculateMontantPaye(FactureFournisseur facture, UUID paiementExcluId) {
        return paiementFournisseurRepository.findAll().stream()
                .filter(paiement -> paiement.getFactureFournisseur() != null)
                .filter(paiement -> facture.getId().equals(paiement.getFactureFournisseur().getId()))
                .filter(paiement -> paiementExcluId == null
                        || !paiementExcluId.equals(paiement.getId()))
                .map(PaiementFournisseur::getMontant)
                .filter(montant -> montant != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
