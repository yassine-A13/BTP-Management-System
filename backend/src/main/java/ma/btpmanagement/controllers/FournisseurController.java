package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.fournisseur.FournisseurRequestDTO;
import ma.btpmanagement.dtos.fournisseur.FournisseurResponseDTO;
import ma.btpmanagement.services.FournisseurService;
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
@RequestMapping("/api/v1/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @PostMapping
    public ResponseEntity<FournisseurResponseDTO> create(
            @Valid @RequestBody FournisseurRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fournisseurService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<FournisseurResponseDTO>> getAll() {
        return ResponseEntity.ok(fournisseurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FournisseurResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(fournisseurService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FournisseurResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody FournisseurRequestDTO dto
    ) {
        return ResponseEntity.ok(fournisseurService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        fournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
