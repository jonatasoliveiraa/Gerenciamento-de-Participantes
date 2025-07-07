package com.example.gerenciamento_de_participantes.dto;

import com.example.gerenciamento_de_participantes.entities.PersonalDocument;

public class PersonalDocumentDTO {

    private Long id;
    private String documentType;
    private String documentNumber;

    public PersonalDocumentDTO() {
    }

    public PersonalDocumentDTO(Long id, String documentType, String documentNumber) {
        this.id = id;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public PersonalDocumentDTO(PersonalDocument entity) {
        id = entity.getId();
        documentType = entity.getDocumentType();
        documentNumber = entity.getDocumentNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
