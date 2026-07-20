package ma.btpmanagement.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.btpmanagement.dtos.attachement.AttachementRequestDTO;
import ma.btpmanagement.dtos.attachement.AttachementResponseDTO;
import ma.btpmanagement.services.AttachementService;
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
@RequestMapping("/api/v1/attachements")
@RequiredArgsConstructor
public class AttachementController {

    private final AttachementService attachementService;

    @PostMapping
    public ResponseEntity<AttachementResponseDTO> create(
            @Valid @RequestBody AttachementRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attachementService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<AttachementResponseDTO>> getAll() {
        return ResponseEntity.ok(attachementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttachementResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(attachementService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttachementResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody AttachementRequestDTO dto
    ) {
        return ResponseEntity.ok(attachementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        attachementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
