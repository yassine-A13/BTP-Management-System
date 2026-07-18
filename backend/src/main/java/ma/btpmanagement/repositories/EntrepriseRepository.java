package ma.btpmanagement.repositories;

import ma.btpmanagement.entites.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntrepriseRepository extends JpaRepository<Entreprise, UUID> {
}
