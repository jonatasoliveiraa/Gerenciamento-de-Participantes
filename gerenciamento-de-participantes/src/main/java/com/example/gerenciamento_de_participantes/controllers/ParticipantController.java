package com.example.gerenciamento_de_participantes.controllers;

import com.example.gerenciamento_de_participantes.dto.ParticipantDTO;
import com.example.gerenciamento_de_participantes.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "http://localhost:4200")
public class ParticipantController {

    @Autowired
    private ParticipantService service;

    @GetMapping
    public ResponseEntity<Page<ParticipantDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipantDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> createParticipant(@Validated @RequestBody ParticipantDTO dto) {
        dto = service.createParticipant(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ParticipantDTO> updateParticipant(@PathVariable Long id, @Validated @RequestBody ParticipantDTO dto) {
        return ResponseEntity.ok(service.updateParticipant(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        service.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
