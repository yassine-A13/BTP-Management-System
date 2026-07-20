package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.entreprise.EntrepriseRequestDTO;
import ma.btpmanagement.dtos.entreprise.EntrepriseResponseDTO;
import ma.btpmanagement.services.EntrepriseService;
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
@RequestMapping("/api/v1/entreprises")
@RequiredArgsConstructor
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @PostMapping
    public ResponseEntity<EntrepriseResponseDTO> create(
            @Valid @RequestBody EntrepriseRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entrepriseService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<EntrepriseResponseDTO>> getAll() {
        return ResponseEntity.ok(entrepriseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrepriseResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(entrepriseService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrepriseResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody EntrepriseRequestDTO dto
    ) {
        return ResponseEntity.ok(entrepriseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
