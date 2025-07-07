package com.example.gerenciamento_de_participantes.controllers;

import com.example.gerenciamento_de_participantes.dto.AddressDTO;
import com.example.gerenciamento_de_participantes.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> insertAddress(@PathVariable Long id, @Validated @RequestBody AddressDTO dto) {
        dto = service.insertAddress(id, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Validated @RequestBody AddressDTO dto) {
        return ResponseEntity.ok(service.updateAddress(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
