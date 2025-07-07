package com.example.gerenciamento_de_participantes.controllers;

import com.example.gerenciamento_de_participantes.dto.PersonalDocumentDTO;
import com.example.gerenciamento_de_participantes.services.PersonalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonalDocumentController {

    @Autowired
    private PersonalDocumentService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonalDocumentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<PersonalDocumentDTO> insertPersonalDocument(@PathVariable Long id, @Validated @RequestBody PersonalDocumentDTO dto) {
        dto = service.insertPersonalDocument(id, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonalDocumentDTO> updatePersonalDocument(@PathVariable Long id, @Validated @RequestBody PersonalDocumentDTO dto) {
        return ResponseEntity.ok(service.updatePersonalDocument(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePersonalDocument(@PathVariable Long id) {
        service.deletePersonalDocument(id);
        return ResponseEntity.noContent().build();
    }
}
