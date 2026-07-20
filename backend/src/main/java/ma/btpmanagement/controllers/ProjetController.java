package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.projet.ProjetRequestDTO;
import ma.btpmanagement.dtos.projet.ProjetResponseDTO;
import ma.btpmanagement.services.ProjetService;
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
@RequestMapping("/api/v1/projets")
@RequiredArgsConstructor
public class ProjetController {

    private final ProjetService projetService;

    @PostMapping
    public ResponseEntity<ProjetResponseDTO> create(
            @Valid @RequestBody ProjetRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projetService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjetResponseDTO>> getAll() {
        return ResponseEntity.ok(projetService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(projetService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProjetRequestDTO dto
    ) {
        return ResponseEntity.ok(projetService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        projetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
