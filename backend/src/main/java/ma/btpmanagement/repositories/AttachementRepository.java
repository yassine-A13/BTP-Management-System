package ma.btpmanagement.repositories;

import ma.btpmanagement.entites.Attachement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachementRepository extends JpaRepository<Attachement, UUID> {
}
