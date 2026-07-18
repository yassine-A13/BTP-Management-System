package ma.btpmanagement.repositories;

import ma.btpmanagement.entites.FactureFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FactureFournisseurRepository extends JpaRepository<FactureFournisseur, UUID> {
}
