package ma.btpmanagement.repositories;

import ma.btpmanagement.entites.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjetRepository extends JpaRepository<Projet, UUID> {
}
