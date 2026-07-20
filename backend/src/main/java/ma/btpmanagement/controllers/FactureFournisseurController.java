package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.facture.FactureFournisseurRequestDTO;
import ma.btpmanagement.dtos.facture.FactureFournisseurResponseDTO;
import ma.btpmanagement.services.FactureFournisseurService;
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
@RequestMapping("/api/v1/factures-fournisseurs")
@RequiredArgsConstructor
public class FactureFournisseurController {

    private final FactureFournisseurService factureFournisseurService;

    @PostMapping
    public ResponseEntity<FactureFournisseurResponseDTO> create(
            @Valid @RequestBody FactureFournisseurRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(factureFournisseurService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<FactureFournisseurResponseDTO>> getAll() {
        return ResponseEntity.ok(factureFournisseurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureFournisseurResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(factureFournisseurService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureFournisseurResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody FactureFournisseurRequestDTO dto
    ) {
        return ResponseEntity.ok(factureFournisseurService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        factureFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
