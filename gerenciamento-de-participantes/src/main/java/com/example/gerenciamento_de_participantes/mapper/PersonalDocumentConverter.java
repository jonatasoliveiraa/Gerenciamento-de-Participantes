package com.example.gerenciamento_de_participantes.mapper;

import com.example.gerenciamento_de_participantes.dto.PersonalDocumentDTO;
import com.example.gerenciamento_de_participantes.entities.PersonalDocument;

public class PersonalDocumentConverter {

    public static void copyToDto(PersonalDocument entity, PersonalDocumentDTO dto) {
        entity.setDocumentType(dto.getDocumentType() != null ? dto.getDocumentType() : entity.getDocumentType());
        entity.setDocumentNumber(dto.getDocumentNumber() != null ? dto.getDocumentNumber() : entity.getDocumentNumber());
    }
}
