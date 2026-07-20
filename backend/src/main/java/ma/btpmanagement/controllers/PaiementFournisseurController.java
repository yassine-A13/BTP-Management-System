package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurRequestDTO;
import ma.btpmanagement.dtos.paiement.PaiementFournisseurResponseDTO;
import ma.btpmanagement.services.PaiementFournisseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paiements-fournisseurs")
@RequiredArgsConstructor
public class PaiementFournisseurController {

    private final PaiementFournisseurService paiementFournisseurService;

    @PostMapping
    public ResponseEntity<PaiementFournisseurResponseDTO> create(
            @Valid @RequestBody PaiementFournisseurRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paiementFournisseurService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaiementFournisseurResponseDTO>> getAll() {
        return ResponseEntity.ok(paiementFournisseurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaiementFournisseurResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(paiementFournisseurService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaiementFournisseurResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody PaiementFournisseurRequestDTO dto
    ) {
        return ResponseEntity.ok(paiementFournisseurService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        paiementFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
