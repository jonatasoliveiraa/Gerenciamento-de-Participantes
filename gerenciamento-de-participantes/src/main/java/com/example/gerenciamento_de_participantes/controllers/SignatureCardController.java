package com.example.gerenciamento_de_participantes.controllers;

import com.example.gerenciamento_de_participantes.dto.SignatureCardDTO;
import com.example.gerenciamento_de_participantes.services.SignatureCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:4200")
public class SignatureCardController {

    @Autowired
    private SignatureCardService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SignatureCardDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<SignatureCardDTO> insertSignatureCard(@PathVariable Long id, @Validated @RequestBody SignatureCardDTO dto) {
        dto = service.insertSignatureCard(id, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SignatureCardDTO> updateSignatureCard(@PathVariable Long id, @Validated @RequestBody SignatureCardDTO dto) {
        return ResponseEntity.ok(service.updateSignatureCard(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSignatureCard(@PathVariable Long id) {
        service.deleteSignatureCard(id);
        return ResponseEntity.noContent().build();
    }
}
