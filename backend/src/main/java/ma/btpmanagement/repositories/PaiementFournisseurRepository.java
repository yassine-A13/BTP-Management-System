package ma.btpmanagement.repositories;

import ma.btpmanagement.entites.PaiementFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaiementFournisseurRepository extends JpaRepository<PaiementFournisseur, UUID> {
}
