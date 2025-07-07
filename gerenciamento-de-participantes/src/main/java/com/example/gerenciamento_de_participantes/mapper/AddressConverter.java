package com.example.gerenciamento_de_participantes.mapper;

import com.example.gerenciamento_de_participantes.dto.AddressDTO;
import com.example.gerenciamento_de_participantes.entities.Address;

public class AddressConverter {

    public static void copyToDto(Address entity, AddressDTO dto) {
        entity.setStreet(dto.getStreet() != null ? dto.getStreet() : entity.getStreet());
        entity.setNeighborhood(dto.getNeighborhood() != null ? dto.getNeighborhood() : entity.getNeighborhood());
        entity.setCity(dto.getCity() != null ? dto.getCity() : entity.getCity());
        entity.setState(dto.getState() != null ? dto.getState() : entity.getState());
        entity.setNumber(dto.getNumber() != null ? dto.getNumber() : entity.getNumber());
        entity.setComplement(dto.getComplement() != null ? dto.getComplement() : entity.getComplement());
        entity.setZipCode(dto.getZipCode() != null ? dto.getZipCode() : entity.getZipCode());
    }
}
