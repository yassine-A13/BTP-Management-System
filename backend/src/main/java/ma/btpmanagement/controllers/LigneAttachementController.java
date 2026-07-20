package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.ligneattachement.LigneAttachementRequestDTO;
import ma.btpmanagement.dtos.ligneattachement.LigneAttachementResponseDTO;
import ma.btpmanagement.services.LigneAttachementService;
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
@RequestMapping("/api/v1/lignes-attachements")
@RequiredArgsConstructor
public class LigneAttachementController {

    private final LigneAttachementService ligneAttachementService;

    @PostMapping
    public ResponseEntity<LigneAttachementResponseDTO> create(
            @Valid @RequestBody LigneAttachementRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ligneAttachementService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<LigneAttachementResponseDTO>> getAll() {
        return ResponseEntity.ok(ligneAttachementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneAttachementResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ligneAttachementService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneAttachementResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody LigneAttachementRequestDTO dto
    ) {
        return ResponseEntity.ok(ligneAttachementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ligneAttachementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
