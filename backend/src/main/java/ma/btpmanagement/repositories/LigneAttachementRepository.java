package ma.btpmanagement.repositories;

import java.util.UUID;

import ma.btpmanagement.entites.LigneAttachement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneAttachementRepository extends JpaRepository<LigneAttachement, UUID> {
}
